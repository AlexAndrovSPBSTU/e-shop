package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.models.Comment;
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
    public ResponseEntity<HttpStatus> createComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("comment_id") int id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/photos")
    public ResponseEntity<HttpStatus> deleteCommentPhotos(@RequestParam String[] urls) {
        commentService.deleteCommentPhotos(urls);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
