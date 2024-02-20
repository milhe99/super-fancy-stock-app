package fi.jyu.superfancystockapp.services;

import java.util.List;
import java.util.stream.Collectors;

import fi.jyu.superfancystockapp.dto.request.OrderDTO;
import fi.jyu.superfancystockapp.dto.response.MessageResponseDTO;
import fi.jyu.superfancystockapp.enums.OrderType;
import fi.jyu.superfancystockapp.models.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.repositories.OrderRepository;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private TradeService tradeService;

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllByType(OrderType type) {
        return (List<Order>) orderRepository.findByType(type);
    }

    public String submitOrder(Order newOrder) {
        List<Order> matchingOrders = makeOrderList(newOrder);
        for (Order matchingOrder : matchingOrders) {
            newOrder = updateOrders(newOrder, matchingOrder);
            if (newOrder.getQuantity() <= 0) return "order submitted";
        }
        String message = saveOrder(newOrder);
        return "order submitted" + message;
    }

    public List<Order> makeOrderList(Order newOrder) {
        List<Order> matchingOrders;
        if (newOrder.getType() == OrderType.BID) {
            matchingOrders = makeSortedOfferList(newOrder.getPrice());
        } else {
            matchingOrders = makeSortedBidList(newOrder.getPrice());
        }
        return matchingOrders;
    }

    public List<Order> makeSortedOfferList(int price) {
        List<Order> matchingOrders = orderRepository.findOffersBelowPrice(price);
        // TODO: Sort list
        return matchingOrders;
    }

    public List<Order> makeSortedBidList(int price) {
        List<Order> matchingOrders = orderRepository.findBidsAbovePrice(price);
        // TODO: Sort list
        return matchingOrders;
    }

    public Order updateOrders(Order newOrder, Order matchedOrder) {
        int newOrderQuantity = newOrder.getQuantity();
        int matchedOrderQuantity = matchedOrder.getQuantity();
        int tradeQuantity;
        if (newOrderQuantity < matchedOrderQuantity) {
            orderRepository.findById(matchedOrder.getId()).get().setQuantity(matchedOrderQuantity - newOrderQuantity);
            newOrder.setQuantity(0);
            tradeQuantity = newOrderQuantity;
        } else if (newOrderQuantity == matchedOrderQuantity) {
            orderRepository.deleteById(matchedOrder.getId());
            newOrder.setQuantity(0);
            tradeQuantity = newOrderQuantity;
        } else {
            orderRepository.deleteById(matchedOrder.getId());
            newOrder.setQuantity(newOrderQuantity - matchedOrderQuantity);
            tradeQuantity = matchedOrderQuantity;
        }
        makeTrade(newOrder, matchedOrder, tradeQuantity);
        return newOrder;
    }

    public void makeTrade(Order newOrder, Order matchedOrder, int tradeQuantity) {
        int tradePrice;
        if (newOrder.getPrice() <= matchedOrder.getPrice()) {
            tradePrice = matchedOrder.getPrice();
        } else {
            tradePrice = newOrder.getPrice();
        }
        //Trade newTrade = new Trade(tradeQuantity, tradePrice);
        //tradeService.submitTrade(newTrade);
    }

    public String saveOrder(Order newOrder) {
        if (newOrder.getQuantity()<=0) return "order not saved";
        orderRepository.save(newOrder);
        return "order saved";
    }
}