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


    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.getProductsByCategory(..)) && args(id,page)",
            argNames = "joinPoint,id,page")
    public ResponseEntity<?> validateGetProductsByCategory(ProceedingJoinPoint joinPoint, int id, Integer page) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что страница задана верно
        validatePage(page, errors);

        //Проверяем, что категория существует
        validateCategoryId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }


    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.getCharacteristicsByCategory(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?> validateGetCharacteristicsByCategory(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что категория существует
        validateCategoryId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.createCategory(..)) && args(category,parentId)",
            argNames = "joinPoint,category,parentId")
    public ResponseEntity<?> validateCreateCategory(ProceedingJoinPoint joinPoint, Category category,
                                                    Integer parentId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что категория существует
        validateCategoryId(parentId, errors);

        //Переменная isDivereged необходима. Отвечает за то,будет ли данный класс наследовать хар-ки родителей.
        if (category.getIsDiverged() == null) {
            errors.append("isDiverged - field isDiverged is necessary");
        }

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.deleteCategory(..)) && args(id,parentId)",
            argNames = "joinPoint,id,parentId")
    public ResponseEntity<?> validateDeleteCategory(ProceedingJoinPoint joinPoint, int id,
                                                    Integer parentId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Корневая root категория не может быть удалена. root категория не отправляется на фронт.
        //Эта категория создаётся автоматически.
        if (id == 1) {
            errors.append("id - root category can not be deleted\n");
        }

        //Проверяем, что категория существует
        validateCategoryId(id, errors);

        //Проверяем, что родительская категория существует
        if (parentId != null) {
            validateCategoryId(parentId, errors);
        }

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.insert(..)) && args(id,parentId)",
            argNames = "joinPoint,id,parentId")
    public ResponseEntity<?> validateInsert(ProceedingJoinPoint joinPoint, Integer id, Integer parentId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, вставлеяемая категория не root
        if (id == 1) {
            errors.append("id - root category can not be inserted\n");
        }

        //Проверяем, что данные категории существуют
        validateCategoryId(id, errors);
        validateCategoryId(parentId, errors);

        //Проверяем, что мы не пытаемся вставить категорию в саму себя
        if (id.equals(parentId)) {
            errors.append("category can not be inserted into itself\n");
        }

        //Проверяем, что мы не пытаемся сделать категорию подкатегорией одного из своих сыновей или сыновей сыновей и тд.
        validatePaternity(id, parentId, errors, joinPoint);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CategoriesController.rename(..)) && args(id,newName)",
            argNames = "joinPoint,id,newName")
    public ResponseEntity<?> validateRename(ProceedingJoinPoint joinPoint, int id, String newName) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что категория существует
        validateCategoryId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }
}
