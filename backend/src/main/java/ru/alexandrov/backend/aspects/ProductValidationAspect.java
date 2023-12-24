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
    public ResponseEntity<?> validateGetProductById(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что данных продукт существует
        validateProductId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.createProduct(..)) && args(product,categoryId)",
            argNames = "joinPoint,product,categoryId")
    public ResponseEntity<?> validateCreateProduct(ProceedingJoinPoint joinPoint, Product product, Integer categoryId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что название товара уникально
        validateProductName(product.getName(), errors);

        //Проверяем, что указанная категория существует
        validateCategoryId(categoryId, errors);

        //Проверяем уникальность всех фотографий
        if (product.getPhotos() != null) {
            validatePhotos(product.getPhotos(), errors);
        }

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.deleteProduct(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?> validateDeleteProduct(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем наличие товара в базе данных
        validateProductId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.change(..)) && args(id,name,categoryId)",
            argNames = "joinPoint,id,name,categoryId")
    public ResponseEntity<?> validateChange(ProceedingJoinPoint joinPoint, int id, String name, Integer categoryId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем наличие товара в базе дынных
        validateProductId(id, errors);

        //Проверяем наличие категории
        if (categoryId != null) {
            validateCategoryId(categoryId, errors);
        }

        //Проверяем, что название товара уникально
        if (name != null) {
            validateProductName(name, errors);
        }

        return makeReturnStatement(errors, joinPoint);
    }


    @Around(value = "execution(* ru.alexandrov.backend.controllers.ProductsController.setProductProperty(..)) && args(productId,propertyId)",
            argNames = "joinPoint,productId,propertyId")
    public ResponseEntity<?> validateSetProperty(ProceedingJoinPoint joinPoint, int productId, int propertyId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем наличие товара в базе дынных
        validateProductId(productId, errors);

        //Проверяем наличие свойства в базе дынных
        validateProductId(productId, errors);

        return makeReturnStatement(errors, joinPoint);
    }
}
