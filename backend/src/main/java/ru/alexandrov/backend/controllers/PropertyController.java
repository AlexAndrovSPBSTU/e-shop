package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.services.PropertyService;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createProperty(@RequestBody Property property,
                                                     @RequestParam int characteristicId) {
        propertyService.save(property, characteristicId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{property_id}")
    public ResponseEntity<HttpStatus> deleteProperty(@PathVariable("property_id") int id) {
        propertyService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/rename/{property_id}")
    public ResponseEntity<HttpStatus> rename(@PathVariable("property_id") int id,
                                             @RequestParam String newValue) {
        propertyService.changeValue(id, newValue);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
