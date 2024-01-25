package ru.alexandrov.backend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "property")
@Getter
@Setter
public class Property {
    @Id
    @Column(name = "property_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "characteristic_id", referencedColumnName = "characteristic_id")
    private Characteristic characteristic;

    @ManyToMany
    @JoinTable(name = "product_property",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
