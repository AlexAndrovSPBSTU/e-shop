package ru.alexandrov.backend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.dto.cart.CartItemDTO;
import ru.alexandrov.backend.dto.cart.CartItemResponse;
import ru.alexandrov.backend.dto.cart.CartResponse;
import ru.alexandrov.backend.dto.cart.PurchasesResponse;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.models.cart.CartItem;
import ru.alexandrov.backend.models.cart.CartItemId;
import ru.alexandrov.backend.models.cart.Purchase;
import ru.alexandrov.backend.repositories.CartItemRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.repositories.PurchaseRepository;
import ru.alexandrov.backend.services.ShopService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final static Purchase DEFAULT_PURCHASE = Purchase.builder().id(-1).build();

    @Autowired
    public ShopServiceImpl(CartItemRepository cartItemRepository, CustomerRepository customerRepository,
                           ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public String getCustomerEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Transactional
    public CartResponse getCartItems() {
        AtomicInteger totalCount = new AtomicInteger();
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);

        List<CartItemResponse> cartItems = cartItemRepository.findAllByCustomerAndPurchase(customerRepository.findByEmail(
                        getCustomerEmail()).get(), DEFAULT_PURCHASE).stream()
                .map(cartItem -> {
                            Product product = cartItem.getProductObject();
                            totalCount.addAndGet(cartItem.getTotalCount());
                            totalPrice.updateAndGet(v -> v + product.getPrice() * cartItem.getTotalCount());
                            return CartItemResponse.builder()
                                    .productId(product.getId()).
                                    description(product.getDescription())
                                    .amount(product.getAmount())
                                    .price(product.getPrice())
                                    .name(product.getName())
                                    .discount(product.getDiscount())
                                    .totalCount(cartItem.getTotalCount())
                                    .img((product.getPhotos() != null && !product.getPhotos().isEmpty()) ? product.getPhotos().get(0).getUrl() : null)
                                    .build();
                        }
                ).collect(Collectors.toList());
        return CartResponse.builder().products(cartItems).totalCount(totalCount.get()).totalPrice(totalPrice.get()).build();
    }

    public String addProductToCart(int productId) {
        // Get the currently authenticated customer
        String customerEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Get the product by its ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the product is already in the customer's cart
        Optional<CartItem> existingCartItem = cartItemRepository.findById(CartItemId.builder()
                .customer(customer).purchase(DEFAULT_PURCHASE).product(product).build());

        if (existingCartItem.isPresent()) {
            // If the product is already in the cart, update the quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setTotalCount(cartItem.getTotalCount() + 1);
            cartItemRepository.save(cartItem);
            return "Product has been increased";
        } else {
            // If the product is not in the cart, create a new cart item
            CartItem cartItem = CartItem.builder()
                    .customer(customer)
                    .product(product)
                    .purchase(DEFAULT_PURCHASE)
                    .totalCount(1)
                    .build();
            cartItemRepository.save(cartItem);
            return "Product has been added to the cart";
        }
    }


    public void reduce(int productId) {
        CartItem cartItem = getCartItemByProductAndCustomer(productId);
        if (cartItem.getTotalCount() == 1) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setTotalCount(cartItem.getTotalCount() - 1);
            cartItemRepository.save(cartItem);
        }
    }

    public void deleteFromCart(int productId) {
        cartItemRepository.delete(getCartItemByProductAndCustomer(productId));
    }

    private CartItem getCartItemByProductAndCustomer(int productId) {
        // Get the currently authenticated customer
        Customer customer = customerRepository.findByEmail(getCustomerEmail())
                .get();

        // Get the product by its ID
        Product product = productRepository.findById(productId)
                .get();
        return cartItemRepository.findById(CartItemId.builder()
                .product(product)
                .purchase(DEFAULT_PURCHASE)
                .customer(customer).build()).get();
    }

    @Transactional
    public int buy(int[] id) {
        Purchase purchase = new Purchase();
        purchase.setDate(new Date());
        Customer customer = customerRepository.findByEmail(getCustomerEmail()).get();
        purchase.setCustomer(customer);
        purchase = purchaseRepository.save(purchase);
        for (int i : id) {
            Product product = productRepository.findById(i).get();

            //Присоединяем сущность CartItem к новой покупке
            CartItem cartItem = cartItemRepository.findById(CartItemId.builder()
                    .purchase(DEFAULT_PURCHASE)
                    .customer(customer)
                    .product(product).build()).get();
            cartItemRepository.updatePurchase(customer.getId(), product.getId(), purchase.getId());

            //Для каждого продукта уменьшаем его кол-во в БД
            product.setAmount(product.getAmount() - cartItem.getTotalCount());
            productRepository.save(product);
        }
        return purchase.getId();
    }

    @Transactional
    public List<PurchasesResponse> getMyPurchases() {
        Customer customer = customerRepository.findByEmail(getCustomerEmail())
                .get();
        //Берём все покупки которые совершал пользователь
        List<Purchase> purchases = customer.getPurchases();
        List<PurchasesResponse> purchasesResponses = new ArrayList<>();
        for (Purchase purchase : purchases) {
            PurchasesResponse purchasesResponse = PurchasesResponse
                    .builder()
                    .date(purchase.getDate())
                    .products(purchase.getCartItemList()
                            .stream()
                            .map(cartItem -> CartItemDTO.builder().productId(cartItem.getProduct()).totalCount(cartItem.getTotalCount()).build())
                            .collect(Collectors.toList())).build();
            purchasesResponses.add(purchasesResponse);
        }
        return purchasesResponses;
    }

}

