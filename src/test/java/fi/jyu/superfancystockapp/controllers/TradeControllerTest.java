package fi.jyu.superfancystockapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import fi.jyu.superfancystockapp.repositories.TradeRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TradeControllerTest {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tradeCanBeAdded() {

    }

    @Test
    public void tradeCanBeDeletedById() {

    }

    @Test
    public void tradeCanBeUpdatedById() {

    }

    @Test
    public void tradesCanBeListed() {

    }

    @Test
    public void tradeCanBeListedById() {

    }
}
