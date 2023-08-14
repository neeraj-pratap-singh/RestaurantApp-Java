package customer;

public class CustomerInteractionManager {
    private List<Table> tables;
    private Menu menu;

    public CustomerInteractionManager() {
        tables = new ArrayList<>();
        menu = new Menu();
    }

    public Table getTable(String tableId) {
        return tables.stream().filter(t -> t.getId().equals(tableId)).findFirst().orElse(null);
    }

    public Menu getMenu() {
        return menu;
    }

    public void placeOrder(String tableId, Order order) {
        Table table = getTable(tableId);
        if (table != null) {
            table.placeOrder(order);
        }
    }

    // Other related methods
}
