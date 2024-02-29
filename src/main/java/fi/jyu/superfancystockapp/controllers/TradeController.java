package fi.jyu.superfancystockapp.controllers;

import fi.jyu.superfancystockapp.models.Trade;
import fi.jyu.superfancystockapp.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @GetMapping
    public List<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }

    // FIXME: Remove this
    @GetMapping("/test")
    public Trade test() {
        Trade trade = new Trade();
        trade.setQuantity(100);
        trade.setPrice(1000);
        return trade;
    }
}
