package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characteristic")
@Getter
@Setter
public class Characteristic {
    @Id
    @Column(name = "characteristic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_range")
    private Boolean isRange;

    @Column(name = "from_")
    private Float from;

    @Column(name = "_to")
    private Float to;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "characteristic")
    @JsonIgnore
    private List<Property> properties;

}
