package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.cart.BuyRequest;
import ru.alexandrov.backend.models.cart.CartAdditionRequest;
import ru.alexandrov.backend.models.cart.CartItemDTO;
import ru.alexandrov.backend.services.ShopService;

import java.util.List;

@RestController
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/cart")
    public List<CartItemDTO> getCart() {
        return shopService.getCartItems();
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody CartAdditionRequest cartAdditionRequest) {
        shopService.addProductToCart(cartAdditionRequest);
        return ResponseEntity.ok("Goods have been added to the cart!");
    }
}
