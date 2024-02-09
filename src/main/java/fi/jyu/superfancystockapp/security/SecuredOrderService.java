package fi.jyu.superfancystockapp.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.services.OrderService;
import lombok.AccessLevel;
import lombok.Getter;

@Service
@Getter(AccessLevel.PROTECTED)
public class SecuredOrderService {
    @Autowired
    private OrderService orderService;

    public List<Order> getOrders() {
        // TODO: Do sanization
        return getOrderService().getAllOrders();
    }
}