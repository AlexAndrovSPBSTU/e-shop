package ru.alexandrov.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexandrov.backend.dto.CategoryDTO;
import ru.alexandrov.backend.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.alexandrov.backend.TestsUtil.CATEGORY_EXPECTED_NAME;
import static ru.alexandrov.backend.TestsUtil.CATEGORY_RENAMED_NAME;

@SpringBootTest
public class CategoriesTests {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TestsUtil testsUtil;

    @Test
    public void Save_And_Remove() {
        int categoryId = testsUtil.createCategory();
        //Fetch all the categories
        List<String> categories = categoryService.getRootCategories().stream().map(CategoryDTO::getName)
                .collect(Collectors.toList());

        //Check whether one of them is ours
        assertThat(categories).contains(CATEGORY_EXPECTED_NAME);

        //Remove our category
        categoryService.delete(categoryId);

        //Check if a removal is successful
        categories = categoryService.getRootCategories().stream().map(CategoryDTO::getName).collect(Collectors.toList());
        assertThat(categories).doesNotContain(CATEGORY_EXPECTED_NAME);
    }

    @Test
    public void Rename() {
        int categoryId = testsUtil.createCategory();

        categoryService.rename(categoryId, CATEGORY_RENAMED_NAME);

        List<String> categories = categoryService.getRootCategories().stream().map(CategoryDTO::getName).collect(Collectors.toList());

        assertThat(categories).doesNotContain(CATEGORY_EXPECTED_NAME);
        assertThat(categories).contains(CATEGORY_RENAMED_NAME);

        categoryService.delete(categoryId);
    }

}
