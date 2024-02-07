package fi.jyu.superfancystockapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.security.SecuredOrderService;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@Getter(AccessLevel.PROTECTED)
public class OrderController {
    @Autowired
    private SecuredOrderService securedOrderService;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return getSecuredOrderService().getOrders();
    }
}