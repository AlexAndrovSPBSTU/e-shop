package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.cart.CartResponse;
import ru.alexandrov.backend.dto.cart.PurchasesResponse;

import java.util.List;

@Component
public interface ShopService {

    String getCustomerEmail();

    CartResponse getCartItems();

    String addProductToCart(int productId);

    void reduce(int productId);

    void deleteFromCart(int productId);

    int buy(int[] id);

    List<PurchasesResponse> getMyPurchases();

}

