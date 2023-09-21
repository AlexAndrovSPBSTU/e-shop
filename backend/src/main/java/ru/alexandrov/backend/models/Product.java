package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.util.CategoryListSerializer;
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

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id")
            , inverseJoinColumns = @JoinColumn(name = "category_id")
    )
     @JsonSerialize(using = CategoryListSerializer.class)
    private List<Category> categories;

    @ManyToMany(mappedBy = "products")
    @JsonSerialize(using = PropertyListSerializer.class)
    private List<Property> properties;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> category) {
        this.categories = category;
    }

}
