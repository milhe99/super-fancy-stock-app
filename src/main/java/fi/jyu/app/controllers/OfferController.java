package fi.jyu.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.jyu.app.models.Offer;
import fi.jyu.app.security.SecuredOfferService;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@Getter(AccessLevel.PROTECTED)
public class OfferController {
    @Autowired
    private SecuredOfferService securedOfferService;

    @GetMapping("/offers")
    public List<Offer> getOffers() {
        return getSecuredOfferService().getOffers();
    }
}
