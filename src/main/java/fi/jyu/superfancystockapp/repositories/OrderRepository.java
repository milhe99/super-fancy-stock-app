package fi.jyu.superfancystockapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.jyu.superfancystockapp.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
