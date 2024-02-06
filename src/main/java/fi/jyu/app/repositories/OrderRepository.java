package fi.jyu.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.jyu.app.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
