package authentication;

public class CustomerTable extends User {
    public CustomerTable(String username, String password) {
        super(username, password, "Customer/Table");
    }
}
