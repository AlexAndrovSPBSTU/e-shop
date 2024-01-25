package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Photos;

@Component
public interface PhotosService {

    public void delete(String[] urls) ;

    public void save(Photos photos, Integer productId, Integer commentId) ;
}
