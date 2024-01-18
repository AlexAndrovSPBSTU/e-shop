package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.CharacteristicDTO;

@Aspect
@Component
public class CharacteristicValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CharacteristicsController.createCharacteristic(..)) " +
            "&& args(characteristic,categoryId)",
            argNames = "joinPoint,characteristic,categoryId")
    public ResponseEntity<?>  validateCreateCharacteristic(ProceedingJoinPoint joinPoint, CharacteristicDTO characteristic, int categoryId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем наличие свойства isRange
        validateCharacteristicRange(characteristic.getIsRange(), errors);

        //Проверяем, что категория, к которой мы хотим присоединить новую хар-ку сущестует.
        validateCategoryId(categoryId, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CharacteristicsController.rename(..)) && args(id,newName)",
            argNames = "joinPoint,id,newName")
    public ResponseEntity<?>  validateRename(ProceedingJoinPoint joinPoint, int id, String newName) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем наличие хар-ки
        validateCharacteristicId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CharacteristicsController.deleteCharacteristic(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?>  validateDelete(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем наличие хар-ки
        validateCharacteristicId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

}
