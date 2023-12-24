package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.models.cart.CartResponse;
import ru.alexandrov.backend.services.EmailSenderService;
import ru.alexandrov.backend.services.ShopService;

import java.security.Principal;

@RestController
public class ShopController {
    private final ShopService shopService;
    private final EmailSenderService emailSenderService;

    @Autowired
    public ShopController(ShopService shopService, EmailSenderService emailSenderService) {
        this.shopService = shopService;
        this.emailSenderService = emailSenderService;
    }


    /**
     * Renames the user's cart.
     */
    @GetMapping("/cart")
    public CartResponse getCart(Principal principal) {
        return shopService.getCartItems(principal);
    }


    /**
     * Adds a product to the cart.
     *
     * @param id product's id
     * @return {@code 200} if the product was added to the cart, {@code 409} otherwise
     */
    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestParam int id) {
        return ResponseEntity.ok(shopService.addProductToCart(id));
    }


    /**
     * Reduce a product in the cart.
     *
     * @param id product's id
     * @return {@code 200} if the product was reduced in the cart, {@code 409} otherwise
     */
    @PostMapping("/reduce")
    public ResponseEntity<?> reduceProductFromCart(@RequestParam int id, Principal principal) {
        shopService.reduce(id, principal);
        return ResponseEntity.ok("Product has been reduced!");
    }


    /**
     * Deletes a product in the cart.
     *
     * @param id product's id
     * @return {@code 200} if the product was deleted in the cart, {@code 409} otherwise
     */
    @PostMapping("/delete")
    public ResponseEntity<?> deleteFromCart(@RequestParam int id, Principal principal) {
        shopService.deleteFromCart(id, principal);
        return ResponseEntity.ok("Product has been deleted from cart!");
    }

    /**
     * Buys products.
     *
     * @param id products' id we want to buy
     * @return {@code 200} if a purchase completed, {@code 409} otherwise
     */
    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestParam int[] id, Principal principal) {
        int purcahseId =  shopService.buy(id, principal);
        emailSenderService.sendEmail(purcahseId, principal);
        return ResponseEntity.ok("Purchase completed!");
    }

    /**
     * Returns user's purchases.
     *
     * @return {@code 200} if ok, {@code 409} otherwise
     */
    @GetMapping("/myPurchases")
    public ResponseEntity<?> getMyPurchases(Principal principal) {
        return ResponseEntity.ok(shopService.getMyPurchases(principal));
    }
}
