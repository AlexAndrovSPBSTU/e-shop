package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.dto.CharacteristicDTO;
import ru.alexandrov.backend.services.CharacteristicService;

@RestController
@RequestMapping("/characteristics")
public class CharacteristicsController {
    private final CharacteristicService characteristicServiceImpl;

    @Autowired
    public CharacteristicsController(CharacteristicService characteristicServiceImpl) {
        this.characteristicServiceImpl = characteristicServiceImpl;
    }


    /**
     * Creates new characteristic and assign to an existing category.
     *
     * @param characteristic new characteristic
     * @param categoryId category's id to which we assign new characteristic
     * @return {@code 200} if the category was created, {@code 409} otherwise
     */
    @PostMapping("/new")
    public ResponseEntity<?> createCharacteristic(@RequestBody CharacteristicDTO characteristic,
                                                           @RequestParam int categoryId) {
        characteristicServiceImpl.save(characteristic,categoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    /**
     * Renames an existing characteristic.
     *
     * @param id      characteristic's id
     * @param newName new name
     * @return {@code 200} if the characteristic was renamed, {@code 409} otherwise
     */
    @PatchMapping("/rename/{characteristic_id}")
    public ResponseEntity<?> rename(@PathVariable("characteristic_id") int id,
                                             @RequestParam String newName) {
        characteristicServiceImpl.rename(id, newName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Deletes a characteristic by id.
     *
     * @param id       characteristic's id
     * @return {@code 200} if the category was deleted, {@code 409} otherwise
     */
    @DeleteMapping("/{characteristic_id}")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable("characteristic_id") int id) {
        characteristicServiceImpl.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
