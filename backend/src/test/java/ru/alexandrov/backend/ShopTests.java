package ru.alexandrov.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexandrov.backend.dto.cart.CartItemResponse;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.services.ShopService;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.alexandrov.backend.TestsUtil.PRODUCT_EXPECTED_NAME;

@SpringBootTest
public class ShopTests {
    @Autowired
    private ShopService shopServiceImpl;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestsUtil testsUtil;

    @Test
    @Transactional
    public void Test_Add_To_Cart() {
        int categoryId = testsUtil.createCategory();
        int productId = testsUtil.createProduct(categoryId);
        testsUtil.createCustomerWithUserRole();
        testsUtil.authenticateCustomer();
        //Add our new product to the cart
        shopServiceImpl.addProductToCart(productId);

        //Check that all cartResponse's fields are not null
        assertThat(shopServiceImpl.getCartItems())
                .extracting("products", "totalCount", "totalPrice")
                .allSatisfy(field -> assertThat(field).isNotNull());

        //Check that our cart contains added product
        assertThat(shopServiceImpl.getCartItems().getProducts()).isNotEmpty()
                .map(CartItemResponse::getName).contains(PRODUCT_EXPECTED_NAME);

        //Delete a category and all its products
        categoryRepository.deleteById(categoryId);
    }


    @Test
    @Transactional
    public void Test_Total_Count() {
        int categoryId = testsUtil.createCategory();
        int productId = testsUtil.createProduct(categoryId);
        testsUtil.createCustomerWithUserRole();
        testsUtil.authenticateCustomer();

        //Add our new product to the cart twice so that there will be totalCount - 2
        shopServiceImpl.addProductToCart(productId);
        shopServiceImpl.addProductToCart(productId);

        //Check that our cart contains added product with totalCount - 2
        assertThat(shopServiceImpl.getCartItems().getProducts().get(0).getTotalCount()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void Test_Delete_From_Cart() {
        int categoryId = testsUtil.createCategory();
        int productId = testsUtil.createProduct(categoryId);
        testsUtil.createCustomerWithUserRole();
        testsUtil.authenticateCustomer();

        //Add our new product to the cart twice so that there will be totalCount - 2

        shopServiceImpl.addProductToCart(productId);
        shopServiceImpl.addProductToCart(productId);

        //Reduce product from the cart and check that totalCunt - 1
        shopServiceImpl.reduce(productId);
        assertThat(shopServiceImpl.getCartItems().getProducts().get(0).getTotalCount()).isEqualTo(1);

        //Add product to the cart and delete it from there. Check that the cart is empty.
        shopServiceImpl.addProductToCart(productId);
        shopServiceImpl.deleteFromCart(productId);
        assertThat(shopServiceImpl.getCartItems().getProducts()).isEmpty();
    }


    //То же самое что и в Categories.Rename().
//    @Transactional
//        @Test
    public void Test_Buy() {
        int categoryId = testsUtil.createCategory();
        int productId = testsUtil.createProduct(categoryId);
        testsUtil.createCustomerWithUserRole();
        testsUtil.authenticateCustomer();

        shopServiceImpl.addProductToCart(productId);
        shopServiceImpl.addProductToCart(productId);

        shopServiceImpl.buy(new int[]{productId});

        assertThat(shopServiceImpl.getMyPurchases().get(0).getProducts().get(0).getProductId()).isEqualTo(productId);
    }

}
