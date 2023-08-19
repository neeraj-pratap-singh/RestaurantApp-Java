import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerTableFlow {

    private List<Order> orders = new ArrayList<>();

    public void manageCustomerTable(Scanner scanner) {
        MenuManager.loadMenu();
        System.out.println("Welcome to Customer Table Management");
        while (true) {
            System.out.println("1. View Menu\n2. Place Order\n3. Cancel Order\n4. Ask for Bill\n5. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    MenuManager.listMenu();
                    break;
                case "2":
                    placeOrder(scanner);
                    break;
                case "3":
                    cancelOrder(scanner);
                    break;
                case "4":
                    askForBill(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void placeOrder(Scanner scanner) {
        while (true) {
            System.out.println("Enter menu item Name to order or 'done' to finish:");
            String itemId = scanner.nextLine();
            if ("done".equalsIgnoreCase(itemId)) break;

            System.out.println("Enter quantity:");
            int quantity = Integer.parseInt(scanner.nextLine());

            String orderId = UUID.randomUUID().toString();
            Order order = new Order(orderId, itemId, quantity);
            orders.add(order);

            // Record order in CSV file with status "order accepted"
            writeOrderToCsv(order, "order accepted");
            System.out.println("Order placed for item " + itemId + " with quantity " + quantity);
        }
    }

    private void cancelOrder(Scanner scanner) {
        System.out.println("Enter order index to cancel:");
        int index = Integer.parseInt(scanner.nextLine());

        if (index < 0 || index >= orders.size()) {
            System.out.println("Invalid index!");
        } else {
            Order order = orders.get(index);
            if ("order accepted".equalsIgnoreCase(order.getStatus())) {
                orders.remove(index);
                writeOrderToCsv(order, "canceled");
                System.out.println("Order canceled.");
            } else {
                System.out.println("Order cannot be canceled as it's already being prepared.");
            }
        }
    }

    private void askForBill(Scanner scanner) {
        double total = 0.0;
        for (Order order : orders) {
            double price = MenuManager.getItemPrice(order.getItemId()); // Assuming MenuManager has this method
            total += price * order.getQuantity();
        }

        System.out.println("Total bill: $" + total);
        orders.clear(); // Clearing orders after billing
    }

    private void writeOrderToCsv(Order order, String status) {
        try (FileWriter writer = new FileWriter("orders.csv", true)) {
            writer.write(order.getOrderId() + "," + order.getItemId() + "," +
                         MenuManager.getItemPrice(order.getItemId()) + "," +
                         order.getQuantity() + "," + status + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
