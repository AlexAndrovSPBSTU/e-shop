package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Property;

@Aspect
@Component
public class PropertyValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.PropertiesController.createProperty(..)) && args(property,characteristicId)",
            argNames = "joinPoint,property,characteristicId")
    public ResponseEntity validateCreateProperty(ProceedingJoinPoint joinPoint, Property property, int characteristicId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validatePropertyValue(property.getValue(), errors);
        validateCharacteristicId(characteristicId, errors);
        return makeReturnStatement(errors, joinPoint);
    }


    @Around(value = "execution(* ru.alexandrov.backend.controllers.PropertiesController.deleteProperty(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity validateDeleteProperty(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validatePropertyId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.PropertiesController.rename(..)) && args(id,newValue)",
            argNames = "joinPoint,id,newValue")
    public ResponseEntity validateRenameProperty(ProceedingJoinPoint joinPoint, int id, String newValue) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validatePropertyId(id, errors);
        validatePropertyValue(newValue, errors);
        return makeReturnStatement(errors, joinPoint);
    }

}
