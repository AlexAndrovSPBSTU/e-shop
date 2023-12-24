package ru.alexandrov.backend.aspects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

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
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.repositories.CartItemRepository;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.PhotoRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.repositories.PropertyRepository;

@ContextConfiguration(classes = {CategoryValidationAspect.class})
@ExtendWith(SpringExtension.class)
class CategoryValidationAspectDiffblueTest {
    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryValidationAspect categoryValidationAspect;

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
     * Method under test: {@link CategoryValidationAspect#validateGetProductsByCategory(ProceedingJoinPoint, int, Integer)}
     */
    @Test
    void testValidateGetProductsByCategory() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        ResponseEntity<?> actualValidateGetProductsByCategoryResult = categoryValidationAspect
                .validateGetProductsByCategory(null, 1, 1);
        assertEquals("categoryId - there is no category with such id\n",
                actualValidateGetProductsByCategoryResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateGetProductsByCategoryResult.getStatusCode());
        assertTrue(actualValidateGetProductsByCategoryResult.getHeaders().isEmpty());
        verify(categoryRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CategoryValidationAspect#validateGetCharacteristicsByCategory(ProceedingJoinPoint, int)}
     */
    @Test
    void testValidateGetCharacteristicsByCategory() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        ResponseEntity<?> actualValidateGetCharacteristicsByCategoryResult = categoryValidationAspect
                .validateGetCharacteristicsByCategory(null, 1);
        assertEquals("categoryId - there is no category with such id\n",
                actualValidateGetCharacteristicsByCategoryResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateGetCharacteristicsByCategoryResult.getStatusCode());
        assertTrue(actualValidateGetCharacteristicsByCategoryResult.getHeaders().isEmpty());
        verify(categoryRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CategoryValidationAspect#validateCreateCategory(ProceedingJoinPoint, Category, Integer)}
     */
    @Test
    void testValidateCreateCategory() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(false);

        Category category = new Category();
        category.setCharacteristics(new ArrayList<>());
        category.setChildren(new ArrayList<>());
        category.setId(1);
        category.setIsDiverged(true);
        category.setName("Name");
        category.setParents(new ArrayList<>());
        category.setProducts(new ArrayList<>());
        ResponseEntity<?> actualValidateCreateCategoryResult = categoryValidationAspect.validateCreateCategory(null,
                category, 1);
        assertEquals("categoryId - there is no category with such id\n", actualValidateCreateCategoryResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateCreateCategoryResult.getStatusCode());
        assertTrue(actualValidateCreateCategoryResult.getHeaders().isEmpty());
        verify(categoryRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CategoryValidationAspect#validateCreateCategory(ProceedingJoinPoint, Category, Integer)}
     */
    @Test
    void testValidateCreateCategory2() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(false);

        Category category = new Category();
        category.setCharacteristics(new ArrayList<>());
        category.setChildren(new ArrayList<>());
        category.setId(1);
        category.setIsDiverged(null);
        category.setName("Name");
        category.setParents(new ArrayList<>());
        category.setProducts(new ArrayList<>());
        ResponseEntity<?> actualValidateCreateCategoryResult = categoryValidationAspect.validateCreateCategory(null,
                category, 1);
        assertEquals("categoryId - there is no category with such id\nisDiverged - field isDiverged is necessary",
                actualValidateCreateCategoryResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateCreateCategoryResult.getStatusCode());
        assertTrue(actualValidateCreateCategoryResult.getHeaders().isEmpty());
        verify(categoryRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CategoryValidationAspect#validateDeleteCategory(ProceedingJoinPoint, int, Integer)}
     */
    @Test
    void testValidateDeleteCategory() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        ResponseEntity<?> actualValidateDeleteCategoryResult = categoryValidationAspect.validateDeleteCategory(null, 1,
                1);
        assertEquals("id - root category can not be deleted\n", actualValidateDeleteCategoryResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateDeleteCategoryResult.getStatusCode());
        assertTrue(actualValidateDeleteCategoryResult.getHeaders().isEmpty());
        verify(categoryRepository, atLeast(1)).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CategoryValidationAspect#validateDeleteCategory(ProceedingJoinPoint, int, Integer)}
     */
    @Test
    void testValidateDeleteCategory2() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        ResponseEntity<?> actualValidateDeleteCategoryResult = categoryValidationAspect.validateDeleteCategory(null, 1,
                null);
        assertEquals("id - root category can not be deleted\n", actualValidateDeleteCategoryResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateDeleteCategoryResult.getStatusCode());
        assertTrue(actualValidateDeleteCategoryResult.getHeaders().isEmpty());
        verify(categoryRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CategoryValidationAspect#validateInsert(ProceedingJoinPoint, Integer, Integer)}
     */
    @Test
    void testValidateInsert() throws Throwable {
        Category category = new Category();
        category.setCharacteristics(new ArrayList<>());
        category.setChildren(new ArrayList<>());
        category.setId(1);
        category.setIsDiverged(true);
        category.setName("Name");
        category.setParents(new ArrayList<>());
        category.setProducts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        when(categoryRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ResponseEntity<?> actualValidateInsertResult = categoryValidationAspect.validateInsert(null, 1, 1);
        assertEquals("id - root category can not be inserted\ncategory can not be inserted into itself\n",
                actualValidateInsertResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateInsertResult.getStatusCode());
        assertTrue(actualValidateInsertResult.getHeaders().isEmpty());
        verify(categoryRepository, atLeast(1)).existsById(Mockito.<Integer>any());
        verify(categoryRepository, atLeast(1)).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CategoryValidationAspect#validateRename(ProceedingJoinPoint, int, String)}
     */
    @Test
    void testValidateRename() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        ResponseEntity<?> actualValidateRenameResult = categoryValidationAspect.validateRename(null, 1, "New Name");
        assertEquals("categoryId - there is no category with such id\n", actualValidateRenameResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateRenameResult.getStatusCode());
        assertTrue(actualValidateRenameResult.getHeaders().isEmpty());
        verify(categoryRepository).existsById(Mockito.<Integer>any());
    }
}

