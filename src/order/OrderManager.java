package order;

import java.util.*;
import java.io.*;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    
    public OrderManager() {
        loadOrders();
    }

    public void placeOrder(Order order) {
        orders.add(order);
        saveOrders();
    }

    public void cancelOrder(Order order) {
        orders.remove(order);
        saveOrders();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    private void loadOrders() {
        // TODO: Implement loading orders from CSV
    }

    private void saveOrders() {
        // TODO: Implement saving orders to CSV
    }
}
