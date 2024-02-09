package fi.jyu.superfancystockapp.repositories;

import fi.jyu.superfancystockapp.enums.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fi.jyu.superfancystockapp.models.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.type = ?1")
    List<Order> findByType(OrderType type);
}
