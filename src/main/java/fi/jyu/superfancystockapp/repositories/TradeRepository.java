package fi.jyu.superfancystockapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.jyu.superfancystockapp.models.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
    
}
