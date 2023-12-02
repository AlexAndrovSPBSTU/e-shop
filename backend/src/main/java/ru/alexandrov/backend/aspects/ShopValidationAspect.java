package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.cart.CartAdditionRequest;
import ru.alexandrov.backend.models.cart.BuyRequest;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class ShopValidationAspect extends BasicValidationAspect {
    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.addProductToCart(..)) && args(cartAdditionRequest)",
            argNames = "joinPoint,cartAdditionRequest")
    public ResponseEntity<?> validateBuy(ProceedingJoinPoint joinPoint, CartAdditionRequest cartAdditionRequest) throws Throwable {
        StringBuilder errors = new StringBuilder();
        List<Integer> ids = new ArrayList<>();
        validateProductId(cartAdditionRequest.getId(), errors);
        return new ResponseEntity<>(ids, HttpStatus.CONFLICT);
    }
}
