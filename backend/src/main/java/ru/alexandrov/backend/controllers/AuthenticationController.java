package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.security.AuthenticationRequest;
import ru.alexandrov.backend.services.CustomerService;

@RestController
public class AuthenticationController {
    private final CustomerService customerService;

    @Autowired

    public AuthenticationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Registers a new customer. If there is already user with such
     * email
     *
     * @param customer The new customer.
     * @return {@code 200} if the user was registered, {@code 409} otherwise
     * //     * @see #authenticate(AuthenticationRequest)
     */

    @PostMapping(value = "/register", consumes = {"application/xml", "application/json"})
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return ResponseEntity.ok("Customer has been registered");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return customerService.authenticate(authenticationRequest);
    }

}
