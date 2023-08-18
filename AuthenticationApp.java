
import java.util.*;
import java.io.*;

public class AuthenticationApp {

    private static final Map<String, Branch> branches = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();
    private static final List<String> activityLogs = new ArrayList<>();
    private static final String USERS_CSV_FILE = "users.csv";
    private static final String BRANCHES_CSV_FILE = "branches.csv";
    private static int branchCodeCounter = 1000; // For generating unique branch codes

    public static void main(String[] args) {
        // Load branches and users from CSV
        loadBranches();
        loadUsers();

        Scanner scanner = new Scanner(System.in);
        User user;

        while ((user = authenticate(scanner)) == null) {
            System.out.println("Authentication failed! Try again.");
        }

        System.out.println("Authentication successful! Welcome, " + user.getRole() + ".");

        if ("Admin".equals(user.getRole())) {
            manageAdmin(scanner);
        } else if ("BranchManager".equals(user.getRole())) {
            manageBranchManager(scanner, user.getBranch());
        }

        scanner.close();
    }

    private static void loadBranches() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BRANCHES_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String branchCode = data[0];
                String cityName = data[1];
                String address = data[2];
                String pincode = data[3];

                Branch branch = new Branch(branchCode, cityName, address, pincode);
                branches.put(branchCode, branch);
            }
        } catch (IOException e) {
            System.out.println("Error loading branches: " + e.getMessage());
        }
    }

    private static void saveBranches() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BRANCHES_CSV_FILE))) {
            for (Branch branch : branches.values()) {
                writer.println(branch.getBranchCode() + "," + branch.getCityName() + "," + branch.getAddress() + "," + branch.getPincode());
            }
        } catch (IOException e) {
            System.out.println("Error saving branches: " + e.getMessage());
        }
    }

    private static void addBranch(Scanner scanner) {
        System.out.print("Enter branch city name: ");
        String cityName = scanner.nextLine();
        System.out.print("Enter branch address: ");
        String address = scanner.nextLine();
        System.out.print("Enter branch pincode: ");
        String pincode = scanner.nextLine();

        String branchCode = generateBranchCode();
        Branch branch = new Branch(branchCode, cityName, address, pincode);
        branches.put(branchCode, branch);
        saveBranches(); // Save branches to CSV
        activityLogs.add("Added branch: " + branchCode);
        System.out.println("Branch added successfully!");
    }

    private static String generateBranchCode() {
        return "BR" + (branchCodeCounter++);
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
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "," + (user.getBranch() != null ? user.getBranch().getCityName() : ""));
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

    private static void manageAdmin(Scanner scanner) {
        while (true) {
            System.out.println("1. List Branches\n2. Add Branch\n3. Manage Branch\n4. View Logs\n5. List Users\n6. Manage Menu\n7. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listBranches();
                    break;
                case "2":
                    addBranch(scanner);
                    break;
                case "3":
                    manageBranch(scanner); // Admin can manage any branch
                    break;
                case "4":
                    viewLogs();
                    break;
                case "5":
                    listUsers();
                    break;
                case "6":
                    MenuManager.manageMenu(scanner);
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void manageBranchManager(Scanner scanner, Branch branch) {
        while (true) {
            System.out.println("1. List Users in Branch\n2. Add User\n3. Update User\n4. Remove User\n5. Manage Menu\n6. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listUsersInBranch(branch);
                    break;
                case "2":
                    addUser(scanner, branch);
                    break;
                case "3":
                    updateUser(scanner, branch);
                    break;
                case "4":
                    removeUser(scanner, branch);
                    break;
                case "5":
                    MenuManager.manageMenu(scanner);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void listBranches() {
        if (branches.isEmpty()) {
            System.out.println("No branches available!");
            return;
        }

        System.out.println("List of Branches:");
        for (String branchName : branches.keySet()) {
            System.out.println("- " + branchName);
        }
    }

    private static void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available!");
            return;
        }

        System.out.println("List of Users:");
        for (User user : users.values()) {
            System.out.println("- Username: " + user.getUsername() + ", Role: " + user.getRole() + ", Branch: " + (user.getBranch() != null ? user.getBranch().getCityName() : "N/A"));
        }
    }

    private static void listUsersInBranch(Branch branch) {
        boolean found = false;
        System.out.println("List of Users in Branch: " + branch.getCityName());
        for (User user : users.values()) {
            if (branch.equals(user.getBranch())) {
                System.out.println("- Username: " + user.getUsername() + ", Role: " + user.getRole());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No users found in this branch!");
        }
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
        activityLogs.add("Added user: " + username + " to branch: " + branch.getCityName());
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
        activityLogs.add("Updated user: " + username + " in branch: " + branch.getCityName());
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
        activityLogs.add("Removed user: " + username + " from branch: " + branch.getCityName());
        System.out.println("User removed successfully!");
    }

    private static void viewLogs() {
        for (String log : activityLogs) {
            System.out.println(log);
        }
    }
}