package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Photo;

@Component
@Aspect
public class PhotoValidationAspect extends BasicValidationAspect {
    @Around(value = "execution(* ru.alexandrov.backend.controllers.PhotosController.savePhoto(..)) && args(photo,categoryId,productId)",
            argNames = "joinPoint,photo,categoryId,productId")
    public ResponseEntity validateSavePhoto(ProceedingJoinPoint joinPoint, Photo photo, Integer categoryId, Integer productId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        if (categoryId == null && productId == null) {
            errors.append("Either categoryId or productId must be provided\n");
        }
        return makeReturnStatement(errors, joinPoint);
    }


    @Around(value = "execution(* ru.alexandrov.backend.controllers.PhotosController.deletePhoto(..)) && args (url)",
            argNames = "joinPoint,url")
    public ResponseEntity validateDeletePhoto(ProceedingJoinPoint joinPoint, String url) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validatePhotoUrlForDeleting(url, errors);
        return makeReturnStatement(errors, joinPoint);
    }
}
