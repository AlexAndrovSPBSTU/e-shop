package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Comment;

@Component
@Aspect
public class CommentValidationAspect extends BasicValidationAspect {
    @Around(value = "execution(* ru.alexandrov.backend.controllers.CommentsController.createComment(..)) && args(comment,productId)",
            argNames = "joinPoint,comment,productId")
    public ResponseEntity validateCreateComment(ProceedingJoinPoint joinPoint, Comment comment, int productId) throws Throwable {
        StringBuilder errors = new StringBuilder();
        if (comment.getRating() == null) {
            errors.append("rating - rating must be provided");
        }
        validateProductId(productId, errors);
        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.CommentsController.deleteComment(..)) && args(id)",
            argNames = "joinPoint,id")
    public ResponseEntity validateCreateComment(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        StringBuilder errors = new StringBuilder();
        validateCommentId(id, errors);
        return makeReturnStatement(errors, joinPoint);
    }

}
