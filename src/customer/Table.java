package customer;

public class Table {
    private String id;
    private List<Order> orders;

    public Table(String id) {
        this.id = id;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    // Other related methods
}
