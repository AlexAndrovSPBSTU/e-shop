package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Category;

@Component
@Aspect
public class CategoryValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.getProductsByCategory(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity validateGetProductsByCategory(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateCategoryId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.getCharacteristicsByCategory(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity validateGetCharacteristicsByCategory(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateCategoryId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.createCategory(..)) && args(category,parentId)",
            argNames = "joinPoint,category,parentId")
    public ResponseEntity validateCreateCategory(ProceedingJoinPoint joinPoint, Category category,
                                                 Integer parentId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateCategoryName(category.getName(), errors);
        validateCategoryId(parentId, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.deleteCategory(..)) && args(id,parentId)",
            argNames = "joinPoint,id,parentId")
    public ResponseEntity validateDeleteCategory(ProceedingJoinPoint joinPoint, int id,
                                                 Integer parentId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateCategoryId(id, errors);
        if (parentId != null) {
            validateCategoryId(parentId, errors);
        }
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.insert(..)) && args(id,parentId)",
            argNames = "joinPoint,id,parentId")
    public ResponseEntity validateInsert(ProceedingJoinPoint joinPoint, Integer id, Integer parentId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateCategoryId(id, errors);
        validateCategoryParentId(parentId, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.rename(..)) && args(id,newName)",
            argNames = "joinPoint,id,newName")
    public ResponseEntity validateRename(ProceedingJoinPoint joinPoint, int id, String newName) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateCategoryId(id, errors);
        validateCategoryName(newName, errors);
        return makeReturnStatement(errors, joinPoint);
    }


}
