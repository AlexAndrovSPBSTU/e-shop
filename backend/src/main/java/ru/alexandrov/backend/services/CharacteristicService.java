package ru.alexandrov.backend.services;

import ru.alexandrov.backend.dto.CharacteristicDTO;

public interface CharacteristicService {
    void save(CharacteristicDTO characteristicDTO, int categoryId);

    void rename(int id, String newName);

    void delete(int id);
}
