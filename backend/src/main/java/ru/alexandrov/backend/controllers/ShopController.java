package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.models.cart.CartResponse;
import ru.alexandrov.backend.services.ShopService;

import java.security.Principal;

@RestController
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/cart")
    public CartResponse getCart(Principal principal) {
        return shopService.getCartItems(principal);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestParam int id) {
        return ResponseEntity.ok(shopService.addProductToCart(id));
    }

    @PostMapping("/reduce")
    public ResponseEntity<?> reduceProductFromCart(@RequestParam int id) {
        shopService.reduce(id);
        return ResponseEntity.ok("Product has been reduced!");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFromCart(@RequestParam int id, Principal principal) {
        shopService.deleteFromCart(id, principal);
        return ResponseEntity.ok("Product has been deleted from cart!");
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestParam int[] id, Principal principal) {
        shopService.buy(id, principal);
        return ResponseEntity.ok("Purchase completed!");
    }

    @GetMapping("/myPurchases")
    public ResponseEntity<?> getMyPurchases(Principal principal) {
        return ResponseEntity.ok(shopService.getMyPurchases(principal));
    }
}
