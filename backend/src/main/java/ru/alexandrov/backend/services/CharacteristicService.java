package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.repositories.CharacteristicRepository;

@Service
public class CharacteristicService {
    private final CharacteristicRepository characteristicRepository;

    @Autowired
    public CharacteristicService(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    public void save(Characteristic characteristic, int categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        characteristic.setCategory(category);
        characteristicRepository.save(characteristic);
    }

    public void rename(int id, String newName) {
        Characteristic characteristic = characteristicRepository.findById(id).get();
        characteristic.setName(newName);
        characteristicRepository.save(characteristic);
    }

    public void delete(int id) {
        characteristicRepository.deleteById(id);
    }
}
