package ru.alexandrov.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexandrov.backend.dto.ProductDTO;
import ru.alexandrov.backend.services.CategoryService;
import ru.alexandrov.backend.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.alexandrov.backend.TestsUtil.PRODUCT_EXPECTED_NAME;

@SpringBootTest
public class ProductsTests {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TestsUtil testsUtil;

    @Test
    public void Save_And_Remove() {
        //Create a new category
        int categoryId = testsUtil.createCategory();
        int productId = testsUtil.createProduct(categoryId);

        //Fetch all the products
        List<String> products = categoryService.getCategoryProducts(categoryId, null, null, null, null)
                .stream().map(ProductDTO::getName).collect(Collectors.toList());

        //Check whether one of them is ours
        assertThat(products).contains(PRODUCT_EXPECTED_NAME);

        //Remove our new category with product
        productService.delete(productId);

        //Check if a removal is successful
        products = categoryService.getCategoryProducts(categoryId, null, null, null, null).stream().map(ProductDTO::getName).collect(Collectors.toList());
        assertThat(products).doesNotContain(PRODUCT_EXPECTED_NAME);

        categoryService.delete(categoryId);
    }
}
