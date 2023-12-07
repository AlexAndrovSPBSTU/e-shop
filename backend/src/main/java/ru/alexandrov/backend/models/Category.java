package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "parents")
    private List<Category> children;

    @ManyToMany
    @JoinTable(name = "category_parent_child",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    @JsonIgnore
    private List<Category> parents;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Characteristic> characteristics;


    @JsonProperty("productAmountOfCategory")
    public int getProductAmountOfCategory() {
        return products.size() + children.stream().mapToInt(Category::getProductAmountOfCategory).sum();
    }

    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>(products);
        children.forEach(child -> list.addAll(child.getProducts()));
        return list;
    }

    public List<Category> getChildren() {
        List<Category> list = new ArrayList<>(children);
        children.forEach(child -> list.addAll(child.getChildren()));
        return list;
    }
}
