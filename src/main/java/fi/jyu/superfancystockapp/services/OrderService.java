package fi.jyu.superfancystockapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Order createOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Transactional(readOnly = false)
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}