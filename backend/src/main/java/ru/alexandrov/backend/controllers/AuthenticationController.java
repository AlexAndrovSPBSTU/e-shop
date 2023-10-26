package ru.alexandrov.backend.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.security.AuthenticationRequest;
import ru.alexandrov.backend.security.AuthenticationResponse;
import ru.alexandrov.backend.services.CustomerService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class AuthenticationController {
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;

    @Autowired

    public AuthenticationController(CustomerService customerService,
                                    AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
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
    public ResponseEntity registerCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return ResponseEntity.ok("Customer has been authenticated");
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(
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
