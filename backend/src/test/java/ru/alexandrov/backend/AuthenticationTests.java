package ru.alexandrov.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexandrov.backend.models.AuthenticationRequest;
import ru.alexandrov.backend.models.AuthenticationResponse;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.services.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.alexandrov.backend.TestsUtil.CUSTOMER_EMAIL_EXPECTED;
import static ru.alexandrov.backend.TestsUtil.CUSTOMER_PASSWORD_EXPECTED;

@SpringBootTest
public class AuthenticationTests {
    @Autowired
    private CustomerService customerServiceImpl;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestsUtil testsUtil;

    @Test
    public void Registration_With_ValidData() {
        testsUtil.createCustomerWithUserRole();

        AuthenticationResponse authenticationResponse = (AuthenticationResponse) customerServiceImpl.authenticate(
                AuthenticationRequest.builder().password(CUSTOMER_PASSWORD_EXPECTED).email(CUSTOMER_EMAIL_EXPECTED).build()).getBody();

        assertThat(authenticationResponse)
                .extracting("jwtToken", "customer", "role")
                .allSatisfy(field -> assertThat(field).isNotNull());

        customerRepository.deleteById(authenticationResponse.getCustomer().getId());
    }
}