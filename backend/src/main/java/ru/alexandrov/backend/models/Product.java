package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.ReadOnlyProperty;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.util.PropertyListSerializer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    @JsonSerialize(using = PropertyListSerializer.class)
    private List<Property> properties;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<Photo> photos;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    @JsonProperty("status_amount")
    public String getAmount_status() {
        if (amount > 5) {
            return ProjectConstants.AMOUNT_STATUS_IN_STOCK;
        } else if (amount != 0) {
            return ProjectConstants.AMOUNT_STATUS_FEW;
        } else {
            return ProjectConstants.AMOUNT_STATUS_OUT_OF_STOCK;
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @JsonGetter("category")
    public String getCategory() {
        return category.getName();
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
