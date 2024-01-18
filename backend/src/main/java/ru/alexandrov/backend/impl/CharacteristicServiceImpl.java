package ru.alexandrov.backend.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.dto.CharacteristicDTO;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.services.CharacteristicService;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {
    private final CharacteristicRepository characteristicRepository;
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.characteristicRepository = characteristicRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public void save(CharacteristicDTO characteristicDTO, int categoryId) {
        Characteristic characteristic = modelMapper.map(characteristicDTO, Characteristic.class);
        characteristic.setCategory(categoryRepository.findById(categoryId).get());
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
