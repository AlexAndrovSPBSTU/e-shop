package ru.alexandrov.backend.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.security.AuthenticationRequest;
import ru.alexandrov.backend.security.AuthenticationResponse;
import ru.alexandrov.backend.services.CustomerService;
import ru.alexandrov.backend.util.CustomerValidator;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@RestController
public class AuthenticationController {
    private final CustomerService customerService;
    private final CustomerValidator customerValidator;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(CustomerService customerService,
                                    CustomerValidator customerValidator, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.customerValidator = customerValidator;
        this.authenticationManager = authenticationManager;
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

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = null;
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(ProjectConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            jwt = Jwts.builder().setIssuer("e-shop").setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authority", authentication.getAuthorities().iterator().next().getAuthority())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + ProjectConstants.ONE_HOUR))
                    .signWith(key).compact();
        }
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        Customer customer = customerService.findByEmail(authentication.getName()).get();
        authenticationResponse.setName(customer.getName());
        authenticationResponse.setSurname(customer.getSurname());
        authenticationResponse.setEmail(customer.getEmail());
        authenticationResponse.setJwtToken(jwt);
        return ResponseEntity.ok(authenticationResponse);
    }


}
