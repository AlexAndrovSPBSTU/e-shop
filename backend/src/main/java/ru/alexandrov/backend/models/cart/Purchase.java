package ru.alexandrov.backend.models.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.alexandrov.backend.models.Customer;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Integer id;

    @Column(name = "purchase_date")
    private Date date;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    private List<CartItem> cartItemList;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    //Для отображения даты в формате dd.MM.yyyy
    @Transient
    @JsonIgnore
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public String getDate() {
        return sdf.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return id == purchase.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "date=" + date +
                ", cartItemList=" + cartItemList +
                ", customer=" + customer +
                '}';
    }
}
