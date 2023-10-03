package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.util.ProductListSerializer;
import ru.alexandrov.backend.util.PropertyListSerializer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    private int discount;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Property> properties;

    @OneToMany(mappedBy = "product")
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

    @JsonSerialize(using = PropertyListSerializer.class)
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @JsonGetter("category")
    public Integer getCategory() {
        return category.getId();
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
