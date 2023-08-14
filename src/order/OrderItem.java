package order;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private double totalPrice;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.totalPrice = menuItem.getPrice() * quantity;
    }

    // Getters and setters
}
