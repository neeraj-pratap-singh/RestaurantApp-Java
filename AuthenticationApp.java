
import java.util.*;
import java.io.*;

public class AuthenticationApp {

    private static final Map<String, Branch> branches = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();
    private static final List<String> activityLogs = new ArrayList<>();
    private static final String USERS_CSV_FILE = "users.csv";
    
    public static void main(String[] args) {
        // Load users from CSV
        loadUsers();

        Scanner scanner = new Scanner(System.in);
        User user;

        while ((user = authenticate(scanner)) == null) {
            System.out.println("Authentication failed! Try again.");
        }

        System.out.println("Authentication successful! Welcome, " + user.getRole() + ".");

        if ("Admin".equals(user.getRole())) {
            manageBranches(scanner);
        }

        scanner.close();
    }

    private static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_CSV_FILE))) {
            String line;
            // Default Admin
            users.put("admin", new User("admin", "admin", "Admin", null));

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                String password = data[1];
                String role = data[2];
                String branchName = data.length > 3 ? data[3] : null;

                Branch branch = branchName != null ? branches.get(branchName) : null;
                users.put(username, new User(username, password, role, branch));
                System.out.println(users);
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private static void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_CSV_FILE))) {
            for (User user : users.values()) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "," + (user.getBranch() != null ? user.getBranch().getName() : ""));
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private static User authenticate(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    private static void manageBranches(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Branch\n2. Manage Branch\n3. View Logs\n4. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBranch(scanner);
                    break;
                case "2":
                    manageBranch(scanner);
                    break;
                case "3":
                    viewLogs();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addBranch(Scanner scanner) {
        System.out.print("Enter branch name: ");
        String branchName = scanner.nextLine();
        
        if (branches.containsKey(branchName)) {
            System.out.println("Branch already exists!");
            return;
        }
        
        Branch branch = new Branch(branchName);
        branches.put(branchName, branch);
        activityLogs.add("Added branch: " + branchName);
        System.out.println("Branch added successfully!");
    }

    private static void manageBranch(Scanner scanner) {
        System.out.print("Enter branch name to manage: ");
        String branchName = scanner.nextLine();
        
        Branch branch = branches.get(branchName);
        if (branch == null) {
            System.out.println("Branch not found!");
            return;
        }

        while (true) {
            System.out.println("1. Add User\n2. Update User\n3. Remove User\n4. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addUser(scanner, branch);
                    break;
                case "2":
                    updateUser(scanner, branch);
                    break;
                case "3":
                    removeUser(scanner, branch);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addUser(Scanner scanner, Branch branch) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (BranchManager/Employee/CustomerTable): ");
        String role = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("User already exists!");
            return;
        }

        User user = new User(username, password, role, branch);
        users.put(username, user);
        // Save users to CSV
        saveUsers();
        activityLogs.add("Added user: " + username + " to branch: " + branch.getName());
        System.out.println("User added successfully!");
    }

    private static void updateUser(Scanner scanner, Branch branch) {
        System.out.print("Enter username to update: ");
        String username = scanner.nextLine();
        User user = users.get(username);

        if (user == null || !branch.equals(user.getBranch())) {
            System.out.println("User not found in this branch!");
            return;
        }

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        user.setPassword(password);
        // Save users to CSV
        saveUsers();
        activityLogs.add("Updated user: " + username + " in branch: " + branch.getName());
        System.out.println("User updated successfully!");
    }

    private static void removeUser(Scanner scanner, Branch branch) {
        System.out.print("Enter username to remove: ");
        String username = scanner.nextLine();
        User user = users.get(username);

        if (user == null || !branch.equals(user.getBranch())) {
            System.out.println("User not found in this branch!");
            return;
        }

        users.remove(username);
        // Save users to CSV
        saveUsers();
        activityLogs.add("Removed user: " + username + " from branch: " + branch.getName());
        System.out.println("User removed successfully!");
    }

    private static void viewLogs() {
        for (String log : activityLogs) {
            System.out.println(log);
        }
    }
}

class User {
    private String username;
    private String password;
    private String role;
    private Branch branch;

    public User(String username, String password, String role, Branch branch) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.branch = branch;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public Branch getBranch() {
        return branch;
    }
}

class Branch {
    private String name;

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
