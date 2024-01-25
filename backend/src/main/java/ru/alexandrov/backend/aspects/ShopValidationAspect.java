package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.cart.CheckBeforeBuyResponse;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class ShopValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.addProductToCart(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?> validateAddProductToCart(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что товар, который мы хотим добавить в корзину существует
        validateProductId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.reduceProductFromCart(..)) && args(id,principal)",
            argNames = "joinPoint,id,principal")
    public ResponseEntity<?> validateReduceProductFromCart(ProceedingJoinPoint joinPoint, int id, Principal principal) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что товар, который мы хотим уменьшить в корзине находится там
        validateProductId(id, errors);
        validateCartItem(id, principal, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.deleteFromCart(..)) && args(id,principal)",
            argNames = "joinPoint,id,principal")
    public ResponseEntity<?> validateDeleteFromCart(ProceedingJoinPoint joinPoint, int id, Principal principal) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что товар, который мы хотим удалить из корзины находится там
        validateProductId(id, errors);
        validateCartItem(id, principal, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ShopController.buy(..)) && args(id,principal)",
            argNames = "joinPoint,id,principal")
    public ResponseEntity<?> validateBuy(ProceedingJoinPoint joinPoint, int[] id, Principal principal) throws Throwable {
        List<Integer> soldOutProducts = new ArrayList<>();
        List<Integer> nonExistentProducts = new ArrayList<>();

        //Проверяем, что товар, который мы хотим купить есть в корзине
        for (int i : id) {
            validateCartItemBeforeBuy(i, principal, soldOutProducts, nonExistentProducts);
        }
        if (!soldOutProducts.isEmpty() || !nonExistentProducts.isEmpty()) {
            return new ResponseEntity<>(CheckBeforeBuyResponse.builder().soldOutProducts(soldOutProducts).nonExistentProducts(nonExistentProducts).build(),
                    HttpStatus.CONFLICT);
        } else {
            return (ResponseEntity<?>) joinPoint.proceed();
        }
    }
}
