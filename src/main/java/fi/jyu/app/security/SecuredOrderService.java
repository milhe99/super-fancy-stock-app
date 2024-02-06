package fi.jyu.app.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.app.models.Order;
import fi.jyu.app.services.OrderService;
import lombok.AccessLevel;
import lombok.Getter;

@Service
@Getter(AccessLevel.PROTECTED)
public class SecuredOrderService {
    @Autowired
    private OrderService orderService;

    public List<Order> getOrders() {
        // TODO: Do sanization
        return getOrderService().getOrders();
    }
}
