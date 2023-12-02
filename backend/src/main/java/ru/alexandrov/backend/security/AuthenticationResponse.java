package ru.alexandrov.backend.security;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import ru.alexandrov.backend.models.Customer;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;

    @JsonUnwrapped
    private Customer customer;

    private String role;
}
