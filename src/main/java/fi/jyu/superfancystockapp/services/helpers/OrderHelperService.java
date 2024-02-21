package fi.jyu.superfancystockapp.services.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fi.jyu.superfancystockapp.enums.OrderType;
import fi.jyu.superfancystockapp.models.Order;
import fi.jyu.superfancystockapp.models.Trade;
import fi.jyu.superfancystockapp.repositories.OrderRepository;
import fi.jyu.superfancystockapp.services.OrderService;
import fi.jyu.superfancystockapp.services.TradeService;

@Service
public class OrderHelperService {
    @Autowired
    private OrderService orderService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private OrderRepository orderRepository;

     /**
     * Creates an order if there are no matching orders.
     * Otherwise creates a trade and returns updated Order.
     * @param order Incoming order
     * @return Created order or matched order
     */
    public Order createOrder(Order order) {
        List<Order> matches = returnExistingOrdersMatchingIncomingOrder(order);
        if (matches.isEmpty()) {
            return orderService.createOrder(order);
        } else {
            // After sorting, the first order in the list is the best match
            Order matchedOrder = matches.get(0);

            // Count quantity of stocks getting traded
            int tradeQuantity = handleMatchQuantity(order, matchedOrder);
            int tradePrice = matchedOrder.getPrice();
            
            Trade newTrade = new Trade();
            newTrade.setPrice(tradePrice);
            newTrade.setQuantity(tradeQuantity);
            tradeService.createTrade(newTrade);

            return matchedOrder;
        }
    }

    /**
     * Returns a list of orders that match the incoming order.
     * Bids are matched with offers that are below the bid price.
     * Offers are matched with bids that are above the offer price.
     * @param order Incoming order
     * @return List of orders that match the incoming order
     */
    private List<Order> returnExistingOrdersMatchingIncomingOrder(Order order) {
        Assert.isInstanceOf(OrderType.class, order.getType(), "Order type must be either BID or OFFER");
        switch (order.getType()) {
            case BID:
                List<Order> matchingOffers = orderRepository.findOffersBelowPrice(order.getPrice());
                if (matchingOffers.size() < 2) {
                    return matchingOffers;
                } else {
                    sortOrders(matchingOffers);
                    return matchingOffers;
                }
            case OFFER:
                List<Order> matchingBids = orderRepository.findBidsAbovePrice(order.getPrice());
                if (matchingBids.size() < 2) {
                    return matchingBids;
                } else {
                    sortOrders(matchingBids);
                    return matchingBids;
                }
            default:
                return new ArrayList<Order>();
        }
    }

    /**
     * Sorts the orders by price and time.
     * @param orders List of orders
     */
    private static void sortOrders(List<Order> orders) {
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getType() == OrderType.BID && o2.getType() == OrderType.BID) {
                    int comparisonByPrice = Integer.compare(o2.getPrice(), o1.getPrice());
                    if (comparisonByPrice != 0) {
                        return comparisonByPrice;
                    }
                }

                if (o1.getType() == OrderType.OFFER && o2.getType() == OrderType.OFFER) {
                    int comparisonByPrice = Integer.compare(o1.getPrice(), o2.getPrice());
                    if (comparisonByPrice != 0) {
                        return comparisonByPrice;
                    }
                }

                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
    }


    /**
     * Handle match quantity between two orders.
     * @param incomingOrder Incoming order
     * @param matchedOrder Matching order
     * @return Quantity used to create a trade
     */
    public int handleMatchQuantity(Order incomingOrder, Order matchedOrder) {
        int incomingOrderQuantity = incomingOrder.getQuantity();
        int matchedOrderQuantity = matchedOrder.getQuantity();
        if (incomingOrderQuantity < matchedOrderQuantity) {
            // Existing order must be updated, because not all stocks are traded
            matchedOrder.setQuantity(matchedOrderQuantity - incomingOrderQuantity);
            orderService.createOrder(matchedOrder);
            return incomingOrderQuantity;
        } else if (incomingOrderQuantity == matchedOrderQuantity) {
            // Exisiting order can be removed, because all stocks are traded
            orderService.delete(matchedOrder.getId());
            return incomingOrderQuantity;
        } else {
            // New order must be created, because the incoming order is not fully matched
            orderService.delete(matchedOrder.getId());
            Order newOrder = new Order();
            newOrder.setQuantity(incomingOrderQuantity - matchedOrderQuantity);
            newOrder.setPrice(incomingOrder.getPrice());
            newOrder.setType(incomingOrder.getType());
            orderService.createOrder(newOrder);

            return matchedOrderQuantity;
        }
    }
}