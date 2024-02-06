package fi.jyu.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.app.models.Offer;
import fi.jyu.app.repositories.OfferRepository;
import lombok.AccessLevel;
import lombok.Getter;

@Service
@Getter(AccessLevel.PROTECTED)
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getOffers() {
        return getOfferRepository().findAll();
    }
}
