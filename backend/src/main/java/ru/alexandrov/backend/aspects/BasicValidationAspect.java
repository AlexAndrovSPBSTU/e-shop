package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alexandrov.backend.dto.PhotoDTO;
import ru.alexandrov.backend.dto.PropertyDTO;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.models.cart.CartItem;
import ru.alexandrov.backend.models.cart.CartItemId;
import ru.alexandrov.backend.models.cart.Purchase;
import ru.alexandrov.backend.repositories.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


//В этом классе находятся большинство методов для валидации
//свойств объектов. В каждый метод отпарвляется строка,
//в которую мы складываем все ошибки прохождения валидации.
//В случае отсутсвия ошибок, вызывается эндпоинт, иначе -
//досрочное завершение эндпоинта и отправка 409 кода с ошибками.
public class BasicValidationAspect {
    private CategoryRepository categoryRepository;
    private CharacteristicRepository characteristicRepository;
    private ProductRepository productRepository;
    private PropertyRepository propertyRepository;
    private CustomerRepository customerRepository;
    private PhotoRepository photoRepository;
    private CommentRepository commentRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setCharacteristicRepository(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setPropertyRepository(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Autowired
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    protected ResponseEntity<?> makeReturnStatement(StringBuilder errors, ProceedingJoinPoint joinPoint) throws Throwable {
        if (errors.length() != 0) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.CONFLICT);
        } else {
            return (ResponseEntity<?>) joinPoint.proceed();
        }
    }


    protected void validateCharacteristicRange(Boolean isRange, StringBuilder errors) {
        if (isRange == null) {
            errors.append("isRange - isRange is mandatory\n");
        }
    }

    protected void validatePhotoUrlForDeleting(String url, StringBuilder errors) {
        if (url == null || url.isEmpty()) {
            errors.append("url - url is mandatory\n");
        } else {
            if (!photoRepository.existsById(url)) {
                errors.append("url - photo with this url doesn't exists\n");
            }
        }
    }

    protected void validatePhotoUrl(String url, StringBuilder errors) {
        if (url != null && !url.isEmpty()) {
            if (photoRepository.existsById(url)) {
                errors.append("url - photo with this url already exists\n");
            }
        }
    }

    protected void validatePhotos(List<PhotoDTO> photos, StringBuilder errors) {
        for (PhotoDTO photo : photos) {
            validatePhotoUrl(photo.getUrl(), errors);
        }
    }

    protected void validateProductName(String name, StringBuilder errors) {
        if (name == null || name.isEmpty()) {
            errors.append("name - Name is mandatory\n");
        } else {
            if (productRepository.findByName(name).isPresent()) {
                errors.append("name - product with this name already exists\n");
            }
        }
    }

    protected void validateCommentId(int id, StringBuilder errors) {
        validateId(commentRepository, id, "commentId", "comment", errors);
    }

    protected void validateCartItem(int id, Principal principal, StringBuilder errors) {
        Customer customer = customerRepository.findByEmail(principal.getName()).get();
        Product product = productRepository.findById(id).get();
        Optional<CartItem> cartItem = cartItemRepository.findById(CartItemId.builder().customer(customer).purchase(Purchase.builder().id(-1).build()).product(product).build());
        if (!cartItem.isPresent()) {
            errors.append("There is no such product in the cart");
        }
    }

    protected void validateCartItemBeforeBuy(int id, Principal principal, List<Integer> soldOutProducts, List<Integer> nonExistentProducts) {
        Customer customer = customerRepository.findByEmail(principal.getName()).get();
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            nonExistentProducts.add(id);
        } else {
            Optional<CartItem> cartItem = cartItemRepository.findById(CartItemId.builder().customer(customer).purchase(Purchase.builder().id(-1).build()).product(product.get()).build());
            if (!cartItem.isPresent()) {
                nonExistentProducts.add(id);
            } else {
                if (product.get().getAmount() < cartItem.get().getTotalCount()) {
                    soldOutProducts.add(id);
                }
            }
        }
    }

    protected void validateProductId(int id, StringBuilder errors) {
        validateId(productRepository, id, "productId", "product", errors);
    }

    protected void validatePropertyId(int id, StringBuilder errors) {
        validateId(propertyRepository, id, "propertyId", "property", errors);
    }

    protected void validateCategoryId(int id, StringBuilder errors) {
        validateId(categoryRepository, id, "categoryId", "category", errors);
    }

    protected void validateCharacteristicId(int id, StringBuilder errors) {
        validateId(characteristicRepository, id, "characteristicId", "characteristic", errors);
    }

    protected void validateCustomerEmail(String email, StringBuilder errors) {
        if (customerRepository.findByEmail(email).isPresent()) {
            errors.append("email - customer with this email already exists\n");
        }
    }


    protected void validateCustomerEmailExistence(String email, StringBuilder errors) {
        if (!customerRepository.findByEmail(email).isPresent()) {
            errors.append("email - customer with this email doesn't exists\n");
        }
    }

    protected void validatePaternity(int id, int parentId, StringBuilder errors) {
        Category category = categoryRepository.findById(id).get();
        Category parentCategory = categoryRepository.findById(parentId).get();
        if (category.getAllChildren().stream().anyMatch(category1 -> category1.equals(parentCategory))) {
            errors.append("Parent category can not be inserted into one of subcategory.");
        }
    }

    protected void validateProperty(PropertyDTO propertyDTO, StringBuilder errors) {
        if (propertyDTO.getValue() == null) {
            errors.append("value - values is mandatory");
        }
    }

    protected void validatePage(Integer page, StringBuilder errors) {
        if (page != null && page <= 0) {
            errors.append("Page has to be more than 0\n");
        }
    }

    private void validateId(CrudRepository<?, Integer> repository, int id,
                            String varName, String entityName, StringBuilder errors) {
        if (!repository.existsById(id)) {
            errors.append(varName).append(" - there is no ").append(entityName).append(" with such id\n");
        }
    }
}
