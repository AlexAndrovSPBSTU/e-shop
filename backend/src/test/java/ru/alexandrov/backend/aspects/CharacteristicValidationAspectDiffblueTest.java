package ru.alexandrov.backend.aspects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.repositories.CartItemRepository;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.PhotoRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.repositories.PropertyRepository;

@ContextConfiguration(classes = {CharacteristicValidationAspect.class})
@ExtendWith(SpringExtension.class)
class CharacteristicValidationAspectDiffblueTest {
    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CharacteristicRepository characteristicRepository;

    @Autowired
    private CharacteristicValidationAspect characteristicValidationAspect;

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
     * Method under test: {@link CharacteristicValidationAspect#validateCreateCharacteristic(ProceedingJoinPoint, Characteristic, int)}
     */
    @Test
    void testValidateCreateCharacteristic() throws Throwable {
        when(categoryRepository.existsById(Mockito.<Integer>any())).thenReturn(false);

        Category category = new Category();
        category.setCharacteristics(new ArrayList<>());
        category.setChildren(new ArrayList<>());
        category.setId(1);
        category.setIsDiverged(true);
        category.setName("Name");
        category.setParents(new ArrayList<>());
        category.setProducts(new ArrayList<>());

        Characteristic characteristic = new Characteristic();
        characteristic.setCategory(category);
        characteristic.setFrom(10.0f);
        characteristic.setId(1);
        characteristic.setIsRange(true);
        characteristic.setName("Name");
        characteristic.setProperties(new ArrayList<>());
        characteristic.setTo(10.0f);
        ResponseEntity<?> actualValidateCreateCharacteristicResult = characteristicValidationAspect
                .validateCreateCharacteristic(null, characteristic, 1);
        assertEquals("categoryId - there is no category with such id\n",
                actualValidateCreateCharacteristicResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateCreateCharacteristicResult.getStatusCode());
        assertTrue(actualValidateCreateCharacteristicResult.getHeaders().isEmpty());
        verify(categoryRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CharacteristicValidationAspect#validateRename(ProceedingJoinPoint, int, String)}
     */
    @Test
    void testValidateRename() throws Throwable {
        when(characteristicRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        ResponseEntity<?> actualValidateRenameResult = characteristicValidationAspect.validateRename(null, 1, "New Name");
        assertEquals("characteristicId - there is no characteristic with such id\n",
                actualValidateRenameResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateRenameResult.getStatusCode());
        assertTrue(actualValidateRenameResult.getHeaders().isEmpty());
        verify(characteristicRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CharacteristicValidationAspect#validateDelete(ProceedingJoinPoint, int)}
     */
    @Test
    void testValidateDelete() throws Throwable {
        when(characteristicRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        ResponseEntity<?> actualValidateDeleteResult = characteristicValidationAspect.validateDelete(null, 1);
        assertEquals("characteristicId - there is no characteristic with such id\n",
                actualValidateDeleteResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateDeleteResult.getStatusCode());
        assertTrue(actualValidateDeleteResult.getHeaders().isEmpty());
        verify(characteristicRepository).existsById(Mockito.<Integer>any());
    }
}

