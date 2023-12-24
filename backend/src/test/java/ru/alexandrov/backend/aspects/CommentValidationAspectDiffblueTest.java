package ru.alexandrov.backend.aspects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

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
import ru.alexandrov.backend.models.Comment;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.Photo;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.repositories.CartItemRepository;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.PhotoRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.repositories.PropertyRepository;

@ContextConfiguration(classes = {CommentValidationAspect.class})
@ExtendWith(SpringExtension.class)
class CommentValidationAspectDiffblueTest {
    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CharacteristicRepository characteristicRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentValidationAspect commentValidationAspect;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private PhotoRepository photoRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private PropertyRepository propertyRepository;

    /**
     * Method under test: {@link CommentValidationAspect#validateCreateComment(ProceedingJoinPoint, int)}
     */
    @Test
    void testValidateCreateComment() throws Throwable {
        when(commentRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        ResponseEntity<?> actualValidateCreateCommentResult = commentValidationAspect.validateCreateComment(null, 1);
        assertEquals("commentId - there is no comment with such id\n", actualValidateCreateCommentResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateCreateCommentResult.getStatusCode());
        assertTrue(actualValidateCreateCommentResult.getHeaders().isEmpty());
        verify(commentRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CommentValidationAspect#validateCreateComment(ProceedingJoinPoint, Comment, int)}
     */
    @Test
    void testValidateCreateComment2() throws Throwable {
        when(photoRepository.existsById(Mockito.<String>any())).thenReturn(true);
        when(productRepository.existsById(Mockito.<Integer>any())).thenReturn(true);

        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setName("Name");
        customer.setPassword("iloveyou");
        customer.setPurchases(new ArrayList<>());
        customer.setRole("Role");
        customer.setSurname("Doe");

        Customer customer2 = new Customer();
        customer2.setComments(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setName("productId");
        customer2.setPassword("iloveyou");
        customer2.setPurchases(new ArrayList<>());
        customer2.setRole("productId");
        customer2.setSurname("Doe");

        Category category = new Category();
        category.setCharacteristics(new ArrayList<>());
        category.setChildren(new ArrayList<>());
        category.setId(1);
        category.setIsDiverged(true);
        category.setName("productId");
        category.setParents(new ArrayList<>());
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAmount(10);
        product.setCategory(category);
        product.setComments(new ArrayList<>());
        product.setDescription("The characteristics of someone or something");
        product.setDiscount(3);
        product.setId(1);
        product.setName("productId");
        product.setPhotos(new ArrayList<>());
        product.setPrice(10.0d);
        product.setProperties(new ArrayList<>());
        product.setRating(10.0d);
        product.updateRating();

        Comment comment = new Comment();
        comment.setCustomer(customer2);
        comment.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        comment.setId(1);
        comment.setNote("productId");
        comment.setPhotos(new ArrayList<>());
        comment.setProduct(product);
        comment.setRating(1);

        Category category2 = new Category();
        category2.setCharacteristics(new ArrayList<>());
        category2.setChildren(new ArrayList<>());
        category2.setId(1);
        category2.setIsDiverged(true);
        category2.setName("productId");
        category2.setParents(new ArrayList<>());
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAmount(10);
        product2.setCategory(category2);
        product2.setComments(new ArrayList<>());
        product2.setDescription("The characteristics of someone or something");
        product2.setDiscount(3);
        product2.setId(1);
        product2.setName("productId");
        product2.setPhotos(new ArrayList<>());
        product2.setPrice(10.0d);
        product2.setProperties(new ArrayList<>());
        product2.setRating(10.0d);
        product2.updateRating();

        Photo photo = new Photo();
        photo.setComment(comment);
        photo.setProduct(product2);
        photo.setUrl("https://example.org/example");

        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(photo);

        Category category3 = new Category();
        category3.setCharacteristics(new ArrayList<>());
        category3.setChildren(new ArrayList<>());
        category3.setId(1);
        category3.setIsDiverged(true);
        category3.setName("Name");
        category3.setParents(new ArrayList<>());
        category3.setProducts(new ArrayList<>());

        Product product3 = new Product();
        product3.setAmount(10);
        product3.setCategory(category3);
        product3.setComments(new ArrayList<>());
        product3.setDescription("The characteristics of someone or something");
        product3.setDiscount(3);
        product3.setId(1);
        product3.setName("Name");
        product3.setPhotos(new ArrayList<>());
        product3.setPrice(10.0d);
        product3.setProperties(new ArrayList<>());
        product3.setRating(10.0d);
        product3.updateRating();

        Comment comment2 = new Comment();
        comment2.setCustomer(customer);
        comment2.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        comment2.setId(1);
        comment2.setNote("Note");
        comment2.setPhotos(photos);
        comment2.setProduct(product3);
        comment2.setRating(1);
        ResponseEntity<?> actualValidateCreateCommentResult = commentValidationAspect.validateCreateComment(null,
                comment2, 1);
        assertEquals("url - photo with this url already exists\n", actualValidateCreateCommentResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualValidateCreateCommentResult.getStatusCode());
        assertTrue(actualValidateCreateCommentResult.getHeaders().isEmpty());
        verify(photoRepository).existsById(Mockito.<String>any());
        verify(productRepository).existsById(Mockito.<Integer>any());
    }
}

