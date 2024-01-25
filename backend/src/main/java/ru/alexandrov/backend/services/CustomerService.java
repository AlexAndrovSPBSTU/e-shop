package ru.alexandrov.backend.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.AuthenticationRequest;
import ru.alexandrov.backend.models.Customer;

@Component
public interface CustomerService {

    void save(Customer customer);

    ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest);
}
