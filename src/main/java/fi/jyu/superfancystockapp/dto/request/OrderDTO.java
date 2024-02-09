package fi.jyu.superfancystockapp.dto.request;

import fi.jyu.superfancystockapp.enums.OrderType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;

    private Instant dateTime;

    private Integer quantity;

    private Integer price;

    private OrderType type;
}
