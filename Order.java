public class Order {
    private String orderId;
    private String itemId;
    private int quantity;
    private String status;

    public Order(String orderId, String itemId, int quantity) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.status = "order accepted";
    }

    public String getOrderId() {
        return orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
