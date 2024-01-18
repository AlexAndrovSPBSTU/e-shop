package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

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
