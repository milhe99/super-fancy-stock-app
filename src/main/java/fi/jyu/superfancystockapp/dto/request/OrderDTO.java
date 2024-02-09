package fi.jyu.superfancystockapp.dto.request;

import java.time.Instant;

import fi.jyu.superfancystockapp.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
