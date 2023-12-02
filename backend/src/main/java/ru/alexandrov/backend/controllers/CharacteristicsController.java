package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.services.CharacteristicService;

@RestController
@RequestMapping("/characteristics")
public class CharacteristicsController {
    private final CharacteristicService characteristicService;

    @Autowired
    public CharacteristicsController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createCharacteristic(@RequestBody Characteristic characteristic,
                                                           @RequestParam int categoryId) {
        characteristicService.save(characteristic,categoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/rename/{characteristic_id}")
    public ResponseEntity<?> rename(@PathVariable("characteristic_id") int id,
                                             @RequestParam String newName) {
        characteristicService.rename(id, newName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{characteristic_id}")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable("characteristic_id") int id) {
        characteristicService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
