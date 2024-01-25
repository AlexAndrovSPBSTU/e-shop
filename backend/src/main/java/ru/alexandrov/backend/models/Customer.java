package ru.alexandrov.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.alexandrov.backend.models.cart.Purchase;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
//    @Max(30)
    private String name;

    @Column(name = "surname")
//    @Max(30)
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "role")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Purchase> purchases;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
