package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.repositories.*;
import ru.alexandrov.backend.security.AuthenticationRequest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AuthenticationValidationAspect.class})
@ExtendWith(SpringExtension.class)
class AuthenticationValidationAspectDiffblueTest {
    @Autowired
    private AuthenticationValidationAspect authenticationValidationAspect;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CharacteristicRepository characteristicRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private PhotoRepository photoRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private PropertyRepository propertyRepository;

    /**
     * Method under test: {@link AuthenticationValidationAspect#validateCustomerRegistration(ProceedingJoinPoint, Customer)}
     */
    @Test
    void testValidateCustomerRegistration() throws Throwable {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setName("Name");
        customer.setPassword("iloveyou");
        customer.setPurchases(new ArrayList<>());
        customer.setRole("Role");
        customer.setSurname("Doe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setComments(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setName("Name");
        customer2.setPassword("iloveyou");
        customer2.setPurchases(new ArrayList<>());
        customer2.setRole("Role");
        customer2.setSurname("Doe");
        ResponseEntity<?> actualValidateCustomerRegistrationResult = authenticationValidationAspect
                .validateCustomerRegistration(null, customer2);
        assertEquals("email - customer with this email already exists\n",
                actualValidateCustomerRegistrationResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateCustomerRegistrationResult.getStatusCode());
        assertTrue(actualValidateCustomerRegistrationResult.getHeaders().isEmpty());
        verify(customerRepository).findByEmail(Mockito.any());
    }

    /**
     * Method under test: {@link AuthenticationValidationAspect#validateAndProcessCustomerAuthentication(ProceedingJoinPoint, AuthenticationRequest)}
     */
    @Test
    void testValidateAndProcessCustomerAuthentication() throws Throwable {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByEmail(Mockito.any())).thenReturn(emptyResult);

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("jane.doe@example.org");
        authenticationRequest.setPassword("iloveyou");
        ResponseEntity<?> actualValidateAndProcessCustomerAuthenticationResult = authenticationValidationAspect
                .validateAndProcessCustomerAuthentication(null, authenticationRequest);
        assertEquals("email - customer with this email doesn't exists\n",
                actualValidateAndProcessCustomerAuthenticationResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateAndProcessCustomerAuthenticationResult.getStatusCode());
        assertTrue(actualValidateAndProcessCustomerAuthenticationResult.getHeaders().isEmpty());
        verify(customerRepository).findByEmail(Mockito.any());
    }
}

