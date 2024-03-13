package fi.jyu.superfancystockapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fi.jyu.superfancystockapp.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.type = OFFER and o.price <= ?1")
    List<Order> findOffersBelowPrice(float price);

    @Query("select o from Order o where o.type = BID and o.price >= ?1")
    List<Order> findBidsAbovePrice(float price);
}
