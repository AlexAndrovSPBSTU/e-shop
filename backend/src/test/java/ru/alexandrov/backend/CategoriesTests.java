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

    @Test
    public void Save_And_Remove() {
        //Create a new category
        CategoryDTO category = CategoryDTO.builder()
                .name(CATEGORY_EXPECTED_NAME)
                .isDiverged(true)
                .build();

        //Save it
        categoryService.save(category, 1);

        //Fetch all the categories
        List<String> categories = categoryService.getRootCategories().stream().map(CategoryDTO::getName)
                .collect(Collectors.toList());

        //Check whether one of them is ours
        assertThat(categories).contains(CATEGORY_EXPECTED_NAME);

        //Find id of our newly created category
        List<CategoryDTO> categoriesList = categoryService.getRootCategories();
        int id = categoriesList.stream().filter(categoryDTO -> CATEGORY_EXPECTED_NAME.equals(categoryDTO.getName())).limit(1)
                .map(CategoryDTO::getId).findFirst().get();

        //Remove our category
        categoryService.delete(id);

        //Check if a removal is successful
        categories = categoryService.getRootCategories().stream().map(CategoryDTO::getName).collect(Collectors.toList());
        assertThat(categories).doesNotContain(CATEGORY_EXPECTED_NAME);
    }

    //    @Test
    //Хотел написать тест на проверку переименовывания, но поскольку метод должен быть @Transactional
    //строка categoryService.rename(id, RENAMED_NAME) не выполняется. Вернее сказать она выоплняется, но
    // результат её будет только в конце метода во время закрытия сессии.
    //Не знаю как это обойти.
    @Test
    public void Rename() {
        CategoryDTO category = CategoryDTO.builder()
                .name(CATEGORY_EXPECTED_NAME)
                .isDiverged(true)
                .build();

        categoryService.save(category, 1);

        List<CategoryDTO> categoriesList = categoryService.getRootCategories();
        int id = categoriesList.stream().filter(categoryDTO -> CATEGORY_EXPECTED_NAME.equals(categoryDTO.getName())).limit(1)
                .map(CategoryDTO::getId).findAny().get();

        categoryService.rename(id, CATEGORY_RENAMED_NAME);

        List<String> categories = categoryService.getRootCategories().stream().map(CategoryDTO::getName).collect(Collectors.toList());

        assertThat(categories).doesNotContain(CATEGORY_EXPECTED_NAME);
        assertThat(categories).contains(CATEGORY_RENAMED_NAME);

        categoryService.delete(id);
    }

}
