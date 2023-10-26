package ru.alexandrov.backend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alexandrov.backend.repositories.*;

public class BasicValidationAspect {
    private CategoryRepository categoryRepository;
    private CharacteristicRepository characteristicRepository;
    private ProductRepository productRepository;
    private PropertyRepository propertyRepository;
    private CustomerRepository customerRepository;
    private PhotoRepository photoRepository;
    private CommentRepository commentRepository;


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


    protected ResponseEntity makeReturnStatement(StringBuilder errors, ProceedingJoinPoint joinPoint) throws Throwable {
        if (errors.length() != 0) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.CONFLICT);
        } else {
            return (ResponseEntity<String>) joinPoint.proceed();
        }
    }

    protected void validateCategoryName(String name, StringBuilder errors) {
        if (name.isEmpty()) {
            errors.append("name - Name is mandatory\n");
        } else {
            if (categoryRepository.findByName(name).isPresent()) {
                errors.append("name - category with this name already exists\n");
            }
        }
    }

    protected void validateCharacteristicRange(Boolean isRange, StringBuilder errors) {
        if (isRange == null) {
            errors.append("isRange - isRange is mandatory\n");
        }
    }

    protected void validatePropertyValue(String value, StringBuilder errors) {
        if (value == null || value.isEmpty()) {
            errors.append("value -  is mandatory\n");
        } else {
            if (propertyRepository.findByValue(value).isPresent()) {
                errors.append("value - property with this value already exists\n");
            }
        }
    }

    protected void validateCharacteristicName(String name, StringBuilder errors) {
        if (name == null || name.isEmpty()) {
            errors.append("name - Name is mandatory\n");
        } else {
            if (characteristicRepository.findByName(name).isPresent()) {
                errors.append("name - category with this name already exists\n");
            }
        }
    }

    protected void validatePhotoUrl(String url, StringBuilder errors) {
        if (url == null || url.isEmpty()) {
            errors.append("url - Url is mandatory\n");
        } else {
            if (photoRepository.findById(url).isPresent()) {
                errors.append("url - photo with this url already exists\n");
            }
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

    protected void validateProductName(String name, StringBuilder errors) {
        if (name == null || name.isEmpty()) {
            errors.append("name - Name is mandatory\n");
        } else {
            if (productRepository.findByName(name).isPresent()) {
                errors.append("name - photo with this name already exists\n");
            }
        }
    }

    protected void validateCommentId(int id, StringBuilder errors) {
        validateId(commentRepository, id, "commentId", "comment", errors);
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

    protected void validateCategoryParentId(int id, StringBuilder errors) {
        validateId(categoryRepository, id, "parentId", "category", errors);
    }

    protected void validateCustomerEmail(String email, StringBuilder errors) {
        if (customerRepository.findByEmail(email).isPresent()) {
            errors.append("email - customer with this email already exists\n");
        }
    }

    private void validateId(CrudRepository<?, Integer> repository, int id,
                            String varName, String entityName, StringBuilder errors) {
        if (!repository.existsById(id)) {
            errors.append(varName + " - there is no " + entityName + " with such id\n");
        }
    }

}
