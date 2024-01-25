package ru.alexandrov.backend.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.dto.PropertyDTO;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.repositories.PropertyRepository;
import ru.alexandrov.backend.services.PropertyService;

import javax.transaction.Transactional;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final CharacteristicRepository characteristicRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, CharacteristicRepository characteristicRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.characteristicRepository = characteristicRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void changeValue(int id, String newValue) {
        propertyRepository.changeValue(id, newValue);
    }

    public void save(PropertyDTO propertyDTO, int characteristicId) {
        Property property = modelMapper.map(propertyDTO, Property.class);
        property.setCharacteristic(characteristicRepository.findById(characteristicId).get());
        propertyRepository.save(property);
    }

    public void delete(int id) {
        propertyRepository.deleteById(id);
    }

}
