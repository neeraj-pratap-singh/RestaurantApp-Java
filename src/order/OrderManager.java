package order;

import java.util.*;
import java.io.*;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
        loadOrders();
    }

    public void placeOrder(Order order) {
        orders.add(order);
        saveOrders();
    }

    public void updateOrderStatus(String orderId, String status) {
        Order order = getOrder(orderId);
        if (order != null) {
            order.updateStatus(status);
            saveOrders();
        }
    }

    public Order getOrder(String orderId) {
        return orders.stream().filter(order -> order.getId().equals(orderId)).findFirst().orElse(null);
    }

    private void loadOrders() {
        // TODO: Implement loading orders from CSV
    }

    private void saveOrders() {
        // TODO: Implement saving orders to CSV
    }
}
