package fi.jyu.superfancystockapp.models;

import fi.jyu.superfancystockapp.enums.OrderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    @Column(name = "time")
    private Instant dateTime;

    @Column(name = "quantity")
    @Min(1)
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private OrderType type;

}
