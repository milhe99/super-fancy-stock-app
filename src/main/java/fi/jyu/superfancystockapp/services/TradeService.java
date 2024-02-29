package fi.jyu.superfancystockapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.jyu.superfancystockapp.models.Trade;
import fi.jyu.superfancystockapp.repositories.TradeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TradeService {
    @Autowired
    private final TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        return (List<Trade>) tradeRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Trade createTrade(Trade newTrade) {
        return tradeRepository.save(newTrade);
    }
}

