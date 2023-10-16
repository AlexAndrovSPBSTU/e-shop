package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Photo;
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
    public ResponseEntity<HttpStatus> savePhoto(@RequestBody Photo photo,
                                                @RequestParam int id,
                                                @RequestParam String type) {
        photosService.save(photo, id, type);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePhotos(@RequestParam String[] urls) {
        photosService.deletePhotos(urls);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
