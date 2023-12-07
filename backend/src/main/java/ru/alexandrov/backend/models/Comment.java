package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "note")
    private String note;

    @Column(name = "date_time")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.PERSIST)
    private List<Photo> photos;

    @JsonSetter

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        if (this.photos != null)
            this.photos.forEach(photo -> photo.setComment(this));
    }
}
