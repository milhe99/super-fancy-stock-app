package fi.jyu.superfancystockapp.models;

import fi.jyu.superfancystockapp.enums.OrderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @CreatedDate
    @Column(name = "time")
    private ZonedDateTime dateTime;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "type")
    private OrderType type;
}
