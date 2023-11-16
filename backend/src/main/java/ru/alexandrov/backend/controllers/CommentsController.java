package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Comment;
import ru.alexandrov.backend.models.Photo;
import ru.alexandrov.backend.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/new")
    public ResponseEntity createComment(@RequestBody Comment comment,
                                        @RequestParam int productId) {
        for (Photo photo : comment.getPhotos()) {
            photo.setComment(comment);
        }
        commentService.save(comment, productId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable("comment_id") int id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
