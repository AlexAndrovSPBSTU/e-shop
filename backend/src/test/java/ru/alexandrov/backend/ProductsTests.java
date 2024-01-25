package ru.alexandrov.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexandrov.backend.dto.CategoryDTO;
import ru.alexandrov.backend.dto.ProductDTO;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.services.CategoryService;
import ru.alexandrov.backend.services.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.alexandrov.backend.TestsUtil.CATEGORY_EXPECTED_NAME;
import static ru.alexandrov.backend.TestsUtil.PRODUCT_EXPECTED_NAME;

@SpringBootTest
public class ProductsTests {
    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    @Transactional
    public void Save_And_Remove() {
        //Create a new category for that product
        CategoryDTO category = CategoryDTO.builder()
                .name(CATEGORY_EXPECTED_NAME)
                .isDiverged(true)
                .build();

        //Save category
        categoryService.save(category, 1);

        //Find id of our newly created category
        List<CategoryDTO> categoriesList = categoryService.getRootCategories();
        int categoryId = categoriesList.stream().filter(categoryDTO -> CATEGORY_EXPECTED_NAME.equals(categoryDTO.getName())).limit(1)
                .map(CategoryDTO::getId).findFirst().get();

        //Create a new product
        ProductDTO productDTO = ProductDTO.builder()
                .name(PRODUCT_EXPECTED_NAME)
                .build();

        //Save product
        productServiceImpl.save(productDTO, categoryId);

        //Fetch all the products
        List<String> products = categoryService.getCategoryProducts(categoryId, null, null, null, null).stream().map(ProductDTO::getName).collect(Collectors.toList());

        //Check whether one of them is ours
        assertThat(products).contains(PRODUCT_EXPECTED_NAME);

        //Find id of our newly created product
        List<ProductDTO> productDTOS = categoryService.getCategoryProducts(categoryId, null, null, null, null);
        int productId = productDTOS.stream().filter(categoryDTO -> PRODUCT_EXPECTED_NAME.equals(categoryDTO.getName())).limit(1)
                .map(ProductDTO::getId).findAny().get();

        //Remove our product
        productRepository.deleteById(productId);

        //Check if a removal is successful
        products = categoryService.getCategoryProducts(categoryId, null, null, null, null).stream().map(ProductDTO::getName).collect(Collectors.toList());
        assertThat(products).doesNotContain(PRODUCT_EXPECTED_NAME);

        categoryRepository.deleteById(categoryId);
    }
}
