package fi.jyu.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.app.models.Order;
import fi.jyu.app.repositories.OrderRepository;
import lombok.AccessLevel;
import lombok.Getter;

@Service
@Getter(AccessLevel.PROTECTED)
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        return getOrderRepository().findAll();
    }
}
