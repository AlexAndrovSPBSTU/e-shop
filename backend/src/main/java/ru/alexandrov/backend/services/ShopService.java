package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.cart.CartAdditionRequest;
import ru.alexandrov.backend.models.cart.CartItem;
import ru.alexandrov.backend.models.cart.CartItemDTO;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.repositories.CartItemRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShopService(CartItemRepository cartItemRepository, CustomerRepository customerRepository, ProductRepository productRepository, ProductRepository productRepository1) {
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository1;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItemRepository.findAllByCustomer(customerRepository.findByEmail(
                        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get()).stream()
                .map(cartItem -> {
                            Product product = cartItem.getProduct();
                            CartItemDTO cartItemDTO = new CartItemDTO();
                            cartItemDTO.setProductId(product.getId());
                            cartItemDTO.setDescription(product.getDescription());
                            cartItemDTO.setAmount(product.getAmount());
                            cartItemDTO.setPrice(product.getPrice());
                            cartItemDTO.setTotalCount(cartItem.getTotalCount());
                            cartItemDTO.setImg(!product.getPhotos().isEmpty() ? product.getPhotos().get(0).getUrl() : null);
                            return cartItemDTO;
                        }
                ).collect(Collectors.toList());
    }

    public void addProductToCart(CartAdditionRequest cartAdditionRequest) {
        cartItemRepository.save(CartItem.builder()
                .product(productRepository.findById(cartAdditionRequest.getId()).get())
                .customer(customerRepository.findByEmail(
                        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get())
                .totalCount(cartAdditionRequest.getTotalCount())
                .build()
        );
    }
}
