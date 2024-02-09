package fi.jyu.superfancystockapp.services;

import java.util.List;
import java.util.stream.Collectors;

import fi.jyu.superfancystockapp.dto.request.OrderDTO;
import fi.jyu.superfancystockapp.dto.response.MessageResponseDTO;
import fi.jyu.superfancystockapp.enums.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.repositories.OrderRepository;


@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllByType(OrderType type) {
        return (List<Order>) orderRepository.findByType(type);
    }

    public String submitOrder(Order newOrder) {
        orderRepository.save(newOrder);
        return "order submitted";
    }

    public String submitBid(Order newBid) {
        orderRepository.save(newBid);
        List<Order> offer = orderRepository.findByType(OrderType.valueOf("OFFER"));
        return "order submitted, offer count " + offer.size();
    }
    public String submitOffer(Order newOffer) {
        orderRepository.save(newOffer);
        List<Order> bids = orderRepository.findByType(OrderType.valueOf("BID"));
        return "order submitted, bid count " + bids.size();
    }
}