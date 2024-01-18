package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.dto.CommentDTO;
import ru.alexandrov.backend.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }


    /**
     * Creates new comment and assign to an existing product.
     *
     * @param comment new comment
     * @param productId product's id to which new comment will be assigned
     * @return {@code 200} if the comment was assigned, {@code 409} otherwise
     */
    @PostMapping("/new")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO comment,
                                        @RequestParam int productId) {
        commentService.save(comment, productId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Deletes a comment by id.
     *
     * @param id       characteristic's id
     * @return {@code 200} if the comment was deleted, {@code 409} otherwise
     */
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable("comment_id") int id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
