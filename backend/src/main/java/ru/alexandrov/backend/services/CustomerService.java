package ru.alexandrov.backend.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.security.AuthenticationRequest;
import ru.alexandrov.backend.security.AuthenticationResponse;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(ProjectConstants.ROLE_USER);
        customerRepository.save(customer);
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) {
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
            jwt = Jwts.builder().setIssuer(ProjectConstants.JWT_ISSUER).setSubject(ProjectConstants.SUBJECT)
                    .claim(ProjectConstants.EMAIL, authentication.getName())
                    .claim(ProjectConstants.ROLE, authentication.getAuthorities().iterator().next().getAuthority())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + ProjectConstants.ONE_DAY))
                    .signWith(key).compact();
        }
        return ResponseEntity.ok(AuthenticationResponse
                .builder()
                .customer(customerRepository.findByEmail(authentication.getName()).get())
                .role(customerRepository.findByEmail(authentication.getName()).get().getRole().substring(5))
                .jwtToken(jwt).build()
        );
    }
}
