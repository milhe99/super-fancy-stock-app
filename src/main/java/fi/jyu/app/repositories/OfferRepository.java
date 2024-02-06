package fi.jyu.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.jyu.app.models.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    
}
