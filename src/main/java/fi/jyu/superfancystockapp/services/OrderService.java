package fi.jyu.superfancystockapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.repositories.OrderRepository;
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