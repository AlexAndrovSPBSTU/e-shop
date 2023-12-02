package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.util.PropertyListSerializer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
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
    @JsonIgnore
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
}
