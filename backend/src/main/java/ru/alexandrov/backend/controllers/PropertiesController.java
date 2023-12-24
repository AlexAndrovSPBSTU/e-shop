package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.services.PropertyService;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

    private final PropertyService propertyService;

    @Autowired
    public PropertiesController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Creates a new property and assigns to an existing characteristic.
     *
     * @param property         new property
     * @param characteristicId characteristic's id to which we assign new property
     * @return {@code 200} if the category was created, {@code 409} otherwise
     */
    @PostMapping("/new")
    public ResponseEntity<?> createProperty(@RequestBody Property property, @RequestParam int characteristicId) {
        propertyService.save(property, characteristicId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Deletes a property by id.
     *
     * @param id property's id
     * @return {@code 200} if the property was deleted, {@code 409} otherwise
     */
    @DeleteMapping("/{property_id}")
    public ResponseEntity<?> deleteProperty(@PathVariable("property_id") int id) {
        propertyService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Renames an existing property.
     *
     * @param id       property's id
     * @param newValue new value
     * @return {@code 200} if the property was renamed, {@code 409} otherwise
     */
    @PatchMapping("/rename/{property_id}")
    public ResponseEntity<?> rename(@PathVariable("property_id") int id,
                                    @RequestParam String newValue
    ) {
        propertyService.changeValue(id, newValue);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
