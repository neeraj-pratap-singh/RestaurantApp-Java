package authentication;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    // Admin functionalities for Branch Manager
    public void addBranchManager(AuthenticationManager authManager, String username, String password) {
        authManager.addUser(new BranchManager(username, password));
    }

    public void updateBranchManager(AuthenticationManager authManager, String username, String password) {
        authManager.updateUser(username, new BranchManager(username, password));
    }

    public void removeBranchManager(AuthenticationManager authManager, String username) {
        authManager.removeUser(username);
    }

    // Admin functionalities for Employee
    public void addEmployee(AuthenticationManager authManager, String username, String password) {
        authManager.addUser(new Employee(username, password));
    }

    public void updateEmployee(AuthenticationManager authManager, String username, String password) {
        authManager.updateUser(username, new Employee(username, password));
    }

    public void removeEmployee(AuthenticationManager authManager, String username) {
        authManager.removeUser(username);
    }

    // Admin functionalities for CustomerTable
    public void addCustomerTable(AuthenticationManager authManager, String username, String password) {
        authManager.addUser(new CustomerTable(username, password));
    }

    public void updateCustomerTable(AuthenticationManager authManager, String username, String password) {
        authManager.updateUser(username, new CustomerTable(username, password));
    }

    public void removeCustomerTable(AuthenticationManager authManager, String username) {
        authManager.removeUser(username);
    }

    // Similar functions for other user roles
    // ...
}
