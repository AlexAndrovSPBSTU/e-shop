package ru.alexandrov.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.services.CustomerService;

@Component
public class CustomerValidator implements Validator {
    private final CustomerService customerService;

    @Autowired
    public CustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        if (customerService.findByEmail(customer.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Customer with this email is already exist");
        }
    }
}
