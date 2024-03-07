package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty
    private String name;

    @Column(name = "diverged")
    @NotNull
    private Boolean isDiverged;

    @ManyToMany(mappedBy = "parents", fetch = FetchType.EAGER)
    private List<Category> children;

    @ManyToMany
    @JoinTable(name = "category_parent_child",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<Category> parents;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToMany(mappedBy = "category")
    private List<Characteristic> characteristics;


    @JsonProperty("productAmountOfCategory")
    public int getProductAmountOfCategory() {
        int amount = 0;
        if (products != null && !products.isEmpty()) {
            amount += products.size();
        }
        if (children != null && !children.isEmpty()) {
            amount += children.stream().mapToInt(Category::getProductAmountOfCategory).sum();
        }
        return amount;
    }

    public List<Category> getAllChildren() {
        List<Category> list = new ArrayList<>();
        if (children != null) {
            list.addAll(children);
            children.forEach(child -> list.addAll(child.getAllChildren()));
        }
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
