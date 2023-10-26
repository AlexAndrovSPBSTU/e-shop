package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Photo;
import ru.alexandrov.backend.services.PhotosService;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
@RequestMapping("/photos")
public class PhotosController {
    private final PhotosService photosService;

    @Autowired
    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @PostMapping("/new")
    public ResponseEntity savePhoto(@RequestBody Photo photo,
                                                @RequestParam(required = false) Integer categoryId,
                                                @RequestParam(required = false) Integer productId) {
        photosService.save(photo, categoryId, productId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deletePhoto(@RequestParam String url) {
        photosService.delete(url);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
