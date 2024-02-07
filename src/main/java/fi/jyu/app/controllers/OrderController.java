package fi.jyu.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.jyu.app.models.Order;
import fi.jyu.app.security.SecuredOfferService;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@Getter(AccessLevel.PROTECTED)
public class OrderController {
    @Autowired
    private SecuredOfferService securedOrderService;

    @GetMapping("/offers")
    public List<Offer> getOrders() {
        return getSecuredOrderService().getOrders();
    }
}
