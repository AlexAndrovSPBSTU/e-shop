package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.Photos;

@Component
@Aspect
public class PhotoValidationAspect extends BasicValidationAspect {
    @Around(value = "execution(* ru.alexandrov.backend.controllers.PhotosController.savePhoto(..)) && args(photo,commentId,productId)",
            argNames = "joinPoint,photo,commentId,productId")
    public ResponseEntity<?> validateSavePhoto(ProceedingJoinPoint joinPoint, Photos photo, Integer commentId, Integer productId) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Фото обязательно должно быть прикреплено или к комментарию или к продукту
        if (commentId == null && productId == null) {
            errors.append("Either commentId or productId must be provided\n");
        }

        validatePhotos(photo.getPhotos(), errors);

        if (commentId != null) {
            validateCommentId(commentId, errors);
        }

        if (productId != null) {
            validateProductId(productId, errors);
        }

        return makeReturnStatement(errors, joinPoint);
    }


    @Around(value = "execution(* ru.alexandrov.backend.controllers.PhotosController.deletePhoto(..)) && args (url)",
            argNames = "joinPoint,url")
    public ResponseEntity<?> validateDeletePhoto(ProceedingJoinPoint joinPoint, String[] url) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем что все фото находятся в базе данных
        for (String u : url) {
            validatePhotoUrlForDeleting(u, errors);
        }

        return makeReturnStatement(errors, joinPoint);
    }
}
