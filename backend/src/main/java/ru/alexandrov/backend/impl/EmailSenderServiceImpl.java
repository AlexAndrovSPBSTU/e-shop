package ru.alexandrov.backend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.models.cart.CartItem;
import ru.alexandrov.backend.models.cart.Purchase;
import ru.alexandrov.backend.repositories.CartItemRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.PurchaseRepository;
import ru.alexandrov.backend.services.EmailSenderService;

import java.util.Properties;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSenderImpl mailSender;
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSenderImpl mailSender, @Value("${emailUsername}") String username, @Value("${emailPassword}") String password, PurchaseRepository purchaseRepository, CustomerRepository customerRepository, CartItemRepository cartItemRepository) {
        this.mailSender = mailSender;
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    public void sendEmail(int purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Дата покупки ").append(purchase.getDate()).append("\n").append("Товары:\n");
        double summ = 0;
        Customer customer = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        for (CartItem cartItem : cartItemRepository.findAllByCustomerAndPurchase(customer, purchase)) {
            Product product = cartItem.getProductObject();
            stringBuilder.append("\t").append(product.getName()).append(" - ").append(product.getPrice()).append(" * ")
                    .append(cartItem.getTotalCount()).append(" = ").append(cartItem.getTotalCount() * product.getPrice())
                    .append("\n");
            summ += cartItem.getTotalCount() * product.getPrice();
        }
        stringBuilder.append("\n\n").append("Итоговая цена - ").append(summ);

        sendEmail(customer.getEmail(), "Purchase completed", stringBuilder.toString());
    }
}
