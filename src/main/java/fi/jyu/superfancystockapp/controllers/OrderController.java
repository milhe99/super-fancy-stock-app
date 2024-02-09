package fi.jyu.superfancystockapp.controllers;

import java.util.List;

import fi.jyu.superfancystockapp.dto.request.OrderDTO;
import fi.jyu.superfancystockapp.dto.response.MessageResponseDTO;
import fi.jyu.superfancystockapp.enums.OrderType;
import fi.jyu.superfancystockapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import fi.jyu.superfancystockapp.models.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody Order order) {
        return orderService.submitOrder(order);
    }

    @PostMapping("/offers")
    @ResponseStatus(HttpStatus.CREATED)
    public String submitOffer(@RequestBody Order offer) {
        return orderService.submitOffer(offer);
    }

    @PostMapping("/bids")
    @ResponseStatus(HttpStatus.CREATED)
    public String submitBid(@RequestBody Order bid) {
        return orderService.submitBid(bid);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/offers")
    public List<Order> getAllOffers() {
        return orderService.getAllByType(OrderType.valueOf("OFFER"));
    }
    @GetMapping("/bids")
    public List<Order> getAllBids() {
        return orderService.getAllByType(OrderType.valueOf("BID"));
    }
}