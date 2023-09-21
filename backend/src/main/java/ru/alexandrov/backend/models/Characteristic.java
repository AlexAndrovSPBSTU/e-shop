package ru.alexandrov.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @Column(name = "characteristic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_range")
    private boolean isRange;

    @Column(name = "from_")
    private Float from;

    @Column(name = "_to")
    private Float to;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @OneToMany(mappedBy = "characteristic")
    private List<Property> property;

    public Float getFrom() {
        return from;
    }

    public void setFrom(Float from) {
        this.from = from;
    }

    public Float getTo() {
        return to;
    }

    public void setTo(Float to) {
        this.to = to;
    }
    public List<Property> getProperty() {
        return property;
    }

    public void setProperty(List<Property> property) {
        this.property = property;
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

    public boolean isRange() {
        return isRange;
    }

    public void setRange(boolean range) {
        isRange = range;
    }

//    public float getFrom() {
//        return from;
//    }
//
//    public void setFrom(float from) {
//        this.from = from;
//    }

//    public float getTo() {
//        return to;
//    }
//
//    public void setTo(float to) {
//        this.to = to;
//    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
