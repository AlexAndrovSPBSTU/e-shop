package ru.alexandrov.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import ru.alexandrov.backend.filters.JWTTokenValidatorFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration config = new CorsConfiguration();
//                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                        config.setAllowedMethods(Collections.singletonList("*"));
//                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(Collections.singletonList("*"));
//                        config.setExposedHeaders(Collections.singletonList("Authorization"));
//                        config.setMaxAge(3600L);
//                        return config;
//                    }
//                }))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(new RegexRequestMatcher("/register", "POST")).permitAll()
                                .requestMatchers(new RegexRequestMatcher("/authenticate", "POST")).permitAll()
//                        .requestMatchers(new RegexRequestMatcher("/hello", "GET")).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
