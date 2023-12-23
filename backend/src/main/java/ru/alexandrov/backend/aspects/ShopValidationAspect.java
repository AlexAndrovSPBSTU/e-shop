package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@Aspect
public class ShopValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.addProductToCart(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?> validateAddProductToCart(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateProductId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.reduceProductFromCart(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?> validateReduceProductFromCart(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateProductId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.deleteFromCart(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?> validateDeleteFromCart(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateProductId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.buy(..)) && args(id,principal)",
            argNames = "joinPoint,id,principal")
    public ResponseEntity<?> validateBuy(ProceedingJoinPoint joinPoint, int[] id, Principal principal) throws Throwable {
        StringBuilder errors = new StringBuilder();
        for (int i : id) {
            validateProductId(i, errors);
        }
        for (int i : id) {
            validateCartItem(i, principal, errors);
        }

        return makeReturnStatement(errors, joinPoint);
    }
}
