package fi.jyu.app.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.app.models.Offer;
import fi.jyu.app.services.OfferService;
import lombok.AccessLevel;
import lombok.Getter;

@Service
@Getter(AccessLevel.PROTECTED)
public class SecuredOfferService {
    @Autowired
    private OfferService offerService;

    public List<Offer> getOffers() {
        // TODO: Do sanization
        return getOfferService().getOffers();
    }
}
