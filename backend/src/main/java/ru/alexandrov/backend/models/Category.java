package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "diverged")
    private Boolean isDiverged;

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

    @JsonIgnore
    public List<Category> getAllChildren() {
        List<Category> list = new ArrayList<>(children);
        children.forEach(child -> list.addAll(child.getAllChildren()));
        return list;
    }

    public Set<Characteristic> getCharacteristics() {
        if (isDiverged) {
            return Collections.emptySet();
        }
        Set<Characteristic> characteristicList = new HashSet<>(characteristics);
        parents.forEach(parent -> characteristicList.addAll(parent.getCharacteristics()));
        return characteristicList;
    }
}
