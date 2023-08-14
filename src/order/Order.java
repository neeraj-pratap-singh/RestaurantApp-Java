package order;

import java.util.*;

public class Order {
    private int tableNumber;
    private List<FoodItem> items;
    private double totalAmount;

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        items.add(item);
        totalAmount += item.getPrice();
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
        totalAmount -= item.getPrice();
    }

    // Getters and setters
}
