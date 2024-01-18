package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.PropertyDTO;

@Component
public interface PropertyService {

    void changeValue(int id, String newValue) ;

    void save(PropertyDTO propertyDTO, int characteristicId);

    void delete(int id);

}
