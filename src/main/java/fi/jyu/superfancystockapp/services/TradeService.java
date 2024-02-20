package fi.jyu.superfancystockapp.services;

import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.models.Trade;
import fi.jyu.superfancystockapp.repositories.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {
    private final TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        return (List<Trade>) tradeRepository.findAll();
    }

    public void submitTrade(Trade newTrade) {
        tradeRepository.save(newTrade);
    }
}

