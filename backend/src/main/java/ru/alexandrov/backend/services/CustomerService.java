package ru.alexandrov.backend.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.AuthenticationRequest;
import ru.alexandrov.backend.models.Customer;

@Component
public interface CustomerService {

    public void save(Customer customer);

    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest);
}
