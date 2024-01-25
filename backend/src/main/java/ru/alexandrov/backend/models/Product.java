package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import ru.alexandrov.backend.constants.ProjectConstants;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "price")
    @NotNull
    private Double price;

    @Column(name = "amount")
    @NotNull
    private Integer amount;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "discount")
    @NotNull
    private Integer discount;

    @Column(name = "rating")
    @PositiveOrZero
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
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

    @JsonGetter
    public String getCategory() {
        return category.getName();
    }

    @JsonSetter
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        if (this.photos != null) {
            this.photos.forEach(photo -> photo.setProduct(this));
        }
    }

    @JsonGetter
    public Double getRating() {
        Double averageRating = 0.0;
        if (comments != null) {
            for (Comment comment : comments) {
                averageRating += comment.getRating();
            }
        }
        return (comments == null || comments.isEmpty()) ? averageRating : (double) Math.round((averageRating / comments.size()) * 10) / 10;
    }

    public void updateRating() {
        this.rating = getRating();
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
