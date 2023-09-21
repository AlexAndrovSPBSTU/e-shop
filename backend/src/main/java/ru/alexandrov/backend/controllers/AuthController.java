package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.services.CustomerService;
import ru.alexandrov.backend.util.CustomerValidator;

@RestController
public class AuthController {
    private final CustomerService customerService;

    private final CustomerValidator customerValidator;

    @Autowired
    public AuthController(CustomerService customerService, CustomerValidator customerValidator) {
        this.customerService = customerService;
        this.customerValidator = customerValidator;
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerCustomer(@RequestBody Customer customer, BindingResult bindingResult) {
        customerValidator.validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";\n");
            }
            throw new RuntimeException(errors.toString());
        }
        customerService.save(customer);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
