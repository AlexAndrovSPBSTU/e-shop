package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.security.AuthenticationRequest;


@Aspect
@Component
public class AuthenticationValidationAspect extends BasicValidationAspect {

    @Around(value = "execution(* ru.alexandrov.backend.controllers.AuthenticationController.registerCustomer(..)) && args(customer)",
            argNames = "joinPoint,customer")
    public ResponseEntity<?> validateCustomerRegistration(ProceedingJoinPoint joinPoint, Customer customer) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем наличие обязательных свойств нового пользователя
        if (customer.getName() == null) {
            errors.append("name - name is mandatory\n");
        }
        if (customer.getSurname() == null) {
            errors.append("surname - surname is mandatory\n");
        }
        if (customer.getPassword() == null) {
            errors.append("password - password is mandatory\n");
        }
        if (customer.getEmail() == null) {
            errors.append("email - email is mandatory\n");
        } else {
            //Проверяем уникальность email-а нового пользователя
            validateCustomerEmail(customer.getEmail(), errors);
        }

        return makeReturnStatement(errors, joinPoint);
    }

    @Around(value = "execution(* ru.alexandrov.backend.controllers.AuthenticationController.authenticate(..)) && args(authenticationRequest)",
            argNames = "joinPoint,authenticationRequest")
    public ResponseEntity<?> validateAndProcessCustomerAuthentication(ProceedingJoinPoint joinPoint,
                                                                      AuthenticationRequest authenticationRequest) throws Throwable {
        StringBuilder errors = new StringBuilder();

        //Проверяем, что наличие email
        if (authenticationRequest.getEmail() == null) {
            errors.append("email - email is mandatory\n");
        }

        //Проверяем наличие пароля
        if (authenticationRequest.getPassword() == null) {
            errors.append("password - password is mandatory\n");
        }

        validateCustomerEmailExistence(authenticationRequest.getEmail(), errors);

        return makeReturnStatement(errors, joinPoint);
    }

}
