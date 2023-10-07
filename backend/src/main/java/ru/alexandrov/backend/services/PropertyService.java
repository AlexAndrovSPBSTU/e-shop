package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.repositories.PropertyRepository;

import javax.transaction.Transactional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public void changeValue(int id, String newValue) {
        propertyRepository.changeValue(id, newValue);
    }

    public void save(Property property, int characteristicId) {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(characteristicId);
        property.setCharacteristic(characteristic);
        propertyRepository.save(property);
    }

    public void delete(int id) {
        propertyRepository.deleteById(id);
    }
}
