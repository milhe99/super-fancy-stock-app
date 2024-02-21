package fi.jyu.superfancystockapp.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fi.jyu.superfancystockapp.repositories.OrderRepository;
import fi.jyu.superfancystockapp.repositories.TradeRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderControllerTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class MatchingOrders {
        @Test
        public void matchingBidAndOfferGeneratesATrade() {
            
        }

        @Test
        public void matchingOfferAndBidGeneratesATrade() {
            
        }
    }

    @Nested
    class NonMatchingOrders {
        // Initial: A bid exists in the database
        // Action: A new bid is created
        // Expected: The new bid is listed in the database, a match is not made because bids are matched against offers
        @Test
        public void addOrderWithTheSameType() throws Exception {
            ClassPathResource resource = new ClassPathResource("db/json/bidWithBareMinimum.json");
            byte[] bidToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(bidToCreate))
                .andExpect(status().isCreated());

            assertEquals(2, orderRepository.findAll().size());
            assertEquals(0, tradeRepository.findAll().size());
        }
    }

    @Test
    public void ordersCanBeListed() throws Exception {
        this.mockMvc.perform(get("/orders")
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].type").value("BID"));

        assertEquals(1, orderRepository.findAll().size());
    }
}
