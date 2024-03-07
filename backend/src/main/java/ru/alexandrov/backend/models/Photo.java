package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "photo")
@Getter
@Setter
public class Photo {
    @Id
    @Column(name = "url")
    @NotNull
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id")
    @JsonIgnore
    private Comment comment;

    public Photo() {
    }

    public Photo(String url) {
        this.url = url;
    }
}
