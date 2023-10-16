package ru.alexandrov.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.services.CustomerService;

import java.util.Collections;
import java.util.Optional;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthenticationProvider(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<Customer> optionalCustomer = customerService.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            if (passwordEncoder.matches(password, optionalCustomer.get().getPassword())) {
                return new UsernamePasswordAuthenticationToken(email, password,
                        Collections.singletonList(new SimpleGrantedAuthority(optionalCustomer.get().getRole())));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("No user registered with this credentials!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
