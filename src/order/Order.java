package order;

import java.util.List;

public class Order {
    private String id;
    private List<OrderItem> items;
    private String status;
    private double totalAmount;

    public Order(String id, List<OrderItem> items) {
        this.id = id;
        this.items = items;
        this.status = "Placed";
        calculateTotalAmount();
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    private void calculateTotalAmount() {
        totalAmount = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    // Getters and setters
}
