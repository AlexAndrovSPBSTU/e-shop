package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.repositories.PropertyRepository;

import javax.transaction.Transactional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final CharacteristicRepository characteristicRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, CharacteristicRepository characteristicRepository) {
        this.propertyRepository = propertyRepository;
        this.characteristicRepository = characteristicRepository;
    }

    @Transactional
    public void changeValue(int id, String newValue) {
        propertyRepository.changeValue(id, newValue);
    }

    public void save(Property property, int characteristicId) {
        property.setCharacteristic(characteristicRepository.findById(characteristicId).get());
        propertyRepository.save(property);
    }

    public void delete(int id) {
        propertyRepository.deleteById(id);
    }
}
