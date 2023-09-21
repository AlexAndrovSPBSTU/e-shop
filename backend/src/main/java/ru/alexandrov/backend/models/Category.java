package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "category_id")
    private Category parent;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.REMOVE)
    @JsonIgnore
//    @JsonSerialize(using = CustomProductListSerializer.class)
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
        for (Category child : children) {
            list.addAll(child.getProducts());
        }
        return list;
    }
    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
