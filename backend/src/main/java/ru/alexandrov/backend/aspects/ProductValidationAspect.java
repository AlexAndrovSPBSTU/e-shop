package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Product;

@Aspect
@Component
public class ProductValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.getProductById(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity validateGetProductById(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateProductId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.createProduct(..)) && args(product,categoryId)",
            argNames = "joinPoint,product,categoryId")
    public ResponseEntity validateCreateProduct(ProceedingJoinPoint joinPoint, Product product, Integer categoryId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateProductName(product.getName(), errors);
        validateCategoryId(categoryId, errors);
        if (product.getDiscount() == null) product.setDiscount(0);
        if (product.getAmount() == null) product.setAmount(0);
        if (product.getDescription() == null) product.setDescription("");
        if (product.getPrice() == null) product.setPrice(0.0f);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.deleteProduct(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity validateDeleteProduct(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateProductId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.change(..)) && args(id,name,categoryId)",
            argNames = "joinPoint,id,name,categoryId")
    public ResponseEntity validateChange(ProceedingJoinPoint joinPoint, int id, String name, Integer categoryId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateProductId(id, errors);
        validateCategoryId(categoryId, errors);
        if (name != null) {
            validateProductName(name, errors);
        }
        return makeReturnStatement(errors, joinPoint);
    }
}
