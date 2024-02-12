package fi.jyu.superfancystockapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import fi.jyu.superfancystockapp.repositories.OrderRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void orderCanBeAdded() {

    }

    @Test
    public void matchedOrderGeneratesATrade() {
            
    }

    @Test
    public void nonMatchingOrderDoesNotGenerateATrade() {

    }

    @Test
    public void orderCanBeDeletedById() {

    }

    @Test
    public void orderCanBeUpdatedById() {

    }

    @Test
    public void ordersCanBeListed() {

    }

    @Test
    public void orderCanBeListedById() {

    }

    @Test
    public void ordersCanBeListedByType() {

    }
}
