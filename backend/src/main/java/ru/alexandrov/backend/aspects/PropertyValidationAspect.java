package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.PropertyDTO;

@Aspect
@Component
public class PropertyValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.PropertiesController.createProperty(..)) && args(property,characteristicId)",
            argNames = "joinPoint,property,characteristicId")
    public ResponseEntity<?> validateCreateProperty(ProceedingJoinPoint joinPoint, PropertyDTO property, int characteristicId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что характеристика, к которой мы хотим присоединить новое свойство, существует
        validateCharacteristicId(characteristicId, errors);

        validateProperty(property, errors);

        return makeReturnStatement(errors, joinPoint);
    }


    @Around(value = "execution(* ru.alexandrov.backend.controllers.PropertiesController.deleteProperty(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity<?> validateDeleteProperty(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что такое свойство существует перед удалением
        validatePropertyId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.PropertiesController.rename(..)) && args(id,newValue)",
            argNames = "joinPoint,id,newValue")
    public ResponseEntity<?> validateRenameProperty(ProceedingJoinPoint joinPoint, int id, String newValue) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что такое свойство существует перед переименовыванием
        validatePropertyId(id, errors);

        return makeReturnStatement(errors, joinPoint);
    }
}
