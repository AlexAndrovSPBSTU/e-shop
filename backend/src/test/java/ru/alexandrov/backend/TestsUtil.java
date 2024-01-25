package ru.alexandrov.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.dto.CategoryDTO;
import ru.alexandrov.backend.dto.ProductDTO;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.services.CategoryService;
import ru.alexandrov.backend.services.CustomerService;
import ru.alexandrov.backend.services.ProductService;

import java.util.List;

@Component
public class TestsUtil {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private CustomerService customerServiceImpl;

    public static final String PRODUCT_EXPECTED_NAME = "Xiaomi air dots 2";

    public static final String CATEGORY_EXPECTED_NAME = "Xiaomi";
    public static final String CATEGORY_RENAMED_NAME = "Компьютерные мыши";

    public static final String CUSTOMER_NAME_EXPECTED = "bob";
    public static final String CUSTOMER_SURNAME_EXPECTED = "jonson";
    public static final String CUSTOMER_EMAIL_EXPECTED = "jonson@gmail.com";
    public static final String CUSTOMER_PASSWORD_EXPECTED = "12345";


    public int createCategory() {
        //Create a new category for that product
        CategoryDTO category = CategoryDTO.builder()
                .name(CATEGORY_EXPECTED_NAME)
                .isDiverged(true)
                .build();

        //Save category
        categoryService.save(category, 1);

        //Find id of our newly created category
        List<CategoryDTO> categoriesList = categoryService.getRootCategories();

        return categoriesList.stream().filter(categoryDTO -> CATEGORY_EXPECTED_NAME.equals(categoryDTO.getName())).limit(1)
                .map(CategoryDTO::getId).findFirst().get();
    }

    public int createProduct(int categoryId) {
        //Create a new product
        ProductDTO productDTO = ProductDTO.builder()
                .name(PRODUCT_EXPECTED_NAME)
                .build();

        //Save product
        productServiceImpl.save(productDTO, categoryId);

        //Find id of our newly created product
        List<ProductDTO> productDTOS = categoryService.getCategoryProducts(categoryId, null, null, null, null);

        return productDTOS.stream().filter(categoryDTO -> PRODUCT_EXPECTED_NAME.equals(categoryDTO.getName())).limit(1)
                .map(ProductDTO::getId).findAny().get();

    }

    public void createCustomerWithUserRole() {
        //Create a new customer
        Customer customer = Customer.builder()
                .name(CUSTOMER_NAME_EXPECTED)
                .surname(CUSTOMER_SURNAME_EXPECTED)
                .email(CUSTOMER_EMAIL_EXPECTED)
                .password(CUSTOMER_PASSWORD_EXPECTED)
                .role(ProjectConstants.ROLE_USER)
                .build();

        //Save our newly created customer
        customerServiceImpl.save(customer);
    }

    public void createCustomerWithAdminRole() {
        //Create a new customer
        Customer customer = Customer.builder()
                .name(CUSTOMER_NAME_EXPECTED)
                .surname(CUSTOMER_SURNAME_EXPECTED)
                .email(CUSTOMER_EMAIL_EXPECTED)
                .password(CUSTOMER_PASSWORD_EXPECTED)
                .role(ProjectConstants.ROLE_USER)
                .build();

        //Save our newly created customer
        customerServiceImpl.save(customer);
    }

    public void authenticateCustomer() {
        //Authenticate
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        CUSTOMER_EMAIL_EXPECTED,
                        CUSTOMER_PASSWORD_EXPECTED
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
