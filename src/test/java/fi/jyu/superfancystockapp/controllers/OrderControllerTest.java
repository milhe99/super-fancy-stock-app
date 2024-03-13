package fi.jyu.superfancystockapp.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fi.jyu.superfancystockapp.enums.OrderType;
import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.models.Trade;
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

    @BeforeEach
    public void setUp() {
        Order order = new Order();
        order.setType(OrderType.BID);
        order.setPrice(1000);
        order.setQuantity(100);
        orderRepository.save(order);
    }

    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
        tradeRepository.deleteAll();
    }

    // TODO: Add tests to support price validation

    @Nested
    class MatchingOrders {
        // Initial: A bid exists in the database
        // Action: A new offer is created with a price and quantity that matches the bid
        // Expected: A trade is created, existing bid is removed from the database, new offer is not added to the database
        /*@Test
        public void addOrderToGetExactMatch() throws Exception {
            ClassPathResource resource = new ClassPathResource("db/json/offerToExactlyMatchInitialData.json");
            byte[] offerToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(offerToCreate))
                .andExpect(status().isCreated());

            assertEquals(0, orderRepository.findAll().size());
            assertEquals(1, tradeRepository.findAll().size());
        }*/

        // Initial: A bid exists in the database
        // Action: A new offer is created with a price that matches the bid, but the quantity is smaller
        // Expected: A trade is created, existing bid is updated, new offer is not added to the database because all of its quantity was traded
        /*@Test
        public void addOrderToGetSmallerQuantityMatch() throws Exception {
            ClassPathResource resource = new ClassPathResource("db/json/offerToGetSmallerQuantityMatch.json");
            byte[] offerToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(offerToCreate))
                .andExpect(status().isCreated());

            assertEquals(1, orderRepository.findAll().size());
            assertEquals(1, tradeRepository.findAll().size());

            List<Order> orders = orderRepository.findAll();
            Integer orderId = orders.get(0).getId();

            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                assertEquals(50, order.get().getQuantity());
            } else {
                fail("Data was not found");
            }

            List<Trade> trades = tradeRepository.findAll();
            Integer tradeId = trades.get(0).getId();

            Optional<Trade> trade = tradeRepository.findById(tradeId);
            if (trade.isPresent()) {
                assertEquals(50, trade.get().getQuantity());
            } else {
                fail("Data was not found");
            }
        }*/

        // Initial: A bid exists in the database
        // Action: A new offer is created with a price that matches the bid, but the quantity is bigger
        // Expected: A trade is created, existing bid is removed from the database, new offer is added to the database since not all of its quantity was traded
        /*@Test
        public void addOrderToGetBiggerQuantityMatch() throws Exception {
            ClassPathResource resource = new ClassPathResource("db/json/offerToGetBiggerQuantityMatch.json");
            byte[] offerToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(offerToCreate))
                .andExpect(status().isCreated());

            assertEquals(1, orderRepository.findAll().size());
            assertEquals(1, tradeRepository.findAll().size());

            List<Order> orders = orderRepository.findAll();
            Integer orderId = orders.get(0).getId();

            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                assertEquals(50, order.get().getQuantity());
            } else {
                fail("Data was not found");
            }

            List<Trade> trades = tradeRepository.findAll();
            Integer tradeId = trades.get(0).getId();

            Optional<Trade> trade = tradeRepository.findById(tradeId);
            if (trade.isPresent()) {
                assertEquals(100, trade.get().getQuantity());
            } else {
                fail("Data was not found");
            }
        }*/

        // Initial: A bid exists in the database
        // Action: A new offer is created with a price that is smaller than the bid, and the quantity is exactly the same
        // Expected: A trade is created with the price of the bid, existing bid is removed from the database, new offer is not added to the database
        /*@Test
        public void addOfferWithExactQuantityAndSmallerPrice() throws Exception {
            ClassPathResource resource = new ClassPathResource("db/json/offerWithExactQuantityAndSmallerPrice.json");
            byte[] offerToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(offerToCreate))
                .andExpect(status().isCreated());

            assertEquals(0, orderRepository.findAll().size());
            assertEquals(1, tradeRepository.findAll().size());

            List<Trade> trades = tradeRepository.findAll();
            Integer tradeId = trades.get(0).getId();

            Optional<Trade> trade = tradeRepository.findById(tradeId);
            if (trade.isPresent()) {
                assertEquals(100, trade.get().getQuantity());
                assertEquals(1000, trade.get().getPrice());
            } else {
                fail("Data was not found");
            }
        }*/

        // Initial: An offer exists in the database
        // Action: A new bid is created with a price that is bigger than the offer, and the quantity is exactly the same
        // Expected: A trade is created with the price of the bid, existing offer is removed from the database, new bid is not added to the database
        /*@Test
        public void addBidWithExactQuantityAndBiggerPrice() throws Exception {
            List<Order> orders = orderRepository.findAll();
            Integer orderId = orders.get(0).getId();

            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                order.get().setType(OrderType.OFFER);
                orderRepository.save(order.get());
            } else {
                fail("Initial db data was not found");
            }
            
            ClassPathResource resource = new ClassPathResource("db/json/bidWithExactQuantityAndBiggerPrice.json");
            byte[] bidToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(bidToCreate))
                .andExpect(status().isCreated());

            assertEquals(0, orderRepository.findAll().size());
            assertEquals(1, tradeRepository.findAll().size());

            List<Trade> trades = tradeRepository.findAll();
            Integer tradeId = trades.get(0).getId();

            Optional<Trade> trade = tradeRepository.findById(tradeId);
            if (trade.isPresent()) {
                assertEquals(100, trade.get().getQuantity());
                assertEquals(1050, trade.get().getPrice());
            } else {
                fail("Data was not found");
            }
        }*/

    }

    @Nested
    class NonMatchingOrders {
        // Initial: A bid exists in the database
        // Action: A new bid is created
        // Expected: The new bid is listed in the database, a match is not made because bids are matched against offers
        /*@Test
        public void addBidWithTheSameType() throws Exception {
            ClassPathResource resource = new ClassPathResource("db/json/bidWithBareMinimum.json");
            byte[] bidToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(bidToCreate))
                .andExpect(status().isCreated());

            assertEquals(2, orderRepository.findAll().size());
            assertEquals(0, tradeRepository.findAll().size());
        }*/

        // Initial: An offer exists in the database
        // Action: A new offer is created
        // Expected: The new offer is listed in the database, a match is not made because offers are matched against bids
        /*@Test
        public void addOfferWithTheSameType() throws Exception {
            List<Order> orders = orderRepository.findAll();
            Integer orderId = orders.get(0).getId();

            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                order.get().setType(OrderType.OFFER);
                orderRepository.save(order.get());
            } else {
                fail("Initial db data was not found");
            }

            ClassPathResource resource = new ClassPathResource("db/json/offerWithBareMinimum.json");
            byte[] offerToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(offerToCreate))
                .andExpect(status().isCreated());

            assertEquals(2, orderRepository.findAll().size());
            assertEquals(0, tradeRepository.findAll().size());
        }*/


        // Initial: A bid exists in the database
        // Action: A new offer is created
        // Expected: The new offer is created in the database, a match is not made because offers are matched against bids whose price is higher (than the price of the offer)
        /*@Test
        public void addOfferWithDifferentType() throws Exception {
            ClassPathResource resource = new ClassPathResource("db/json/offerToNotMatchInitialData.json");
            byte[] offerToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(offerToCreate))
                .andExpect(status().isCreated());

            assertEquals(2, orderRepository.findAll().size());
            assertEquals(0, tradeRepository.findAll().size());
        }*/

        // Initial: An offer exists in the database
        // Action: A new bid is created
        // Expected: The new bid is created in the database, a match is not made because bids are matched against offers whose price is lower (than the price of the bid)
        /*@Test
        public void addBidWithDifferentType() throws Exception {
            List<Order> orders = orderRepository.findAll();
            Integer orderId = orders.get(0).getId();

            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                order.get().setType(OrderType.OFFER);
                orderRepository.save(order.get());
            } else {
                fail("Initial db data was not found");
            }

            ClassPathResource resource = new ClassPathResource("db/json/bidToNotMatchInitialData.json");
            byte[] bidToCreate = resource.getInputStream().readAllBytes();

            mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(bidToCreate))
                .andExpect(status().isCreated());

            assertEquals(2, orderRepository.findAll().size());
            assertEquals(0, tradeRepository.findAll().size());
        }*/
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
