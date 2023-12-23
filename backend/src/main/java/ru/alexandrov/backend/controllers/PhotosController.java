package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Photos;
import ru.alexandrov.backend.services.PhotosService;

@Controller
@RequestMapping("/photos")
public class PhotosController {
    private final PhotosService photosService;

    @Autowired
    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> savePhoto(@RequestBody Photos photo,
                                    @RequestParam(required = false) Integer commentId,
                                    @RequestParam(required = false) Integer productId) {
        photosService.save(photo, productId, commentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePhoto(@RequestParam String[] url) {
        photosService.delete(url);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
