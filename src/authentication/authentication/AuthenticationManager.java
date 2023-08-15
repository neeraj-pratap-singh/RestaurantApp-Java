package authentication;

import java.util.*;
import java.io.*;

public class AuthenticationManager implements Authenticatable {
    private List<User> users;

    public AuthenticationManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    @Override
    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
        saveToCSV();
    }

    public void updateUser(String username, User updatedUser) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                user.setRole(updatedUser.getRole());
            }
        }
        saveToCSV();
    }

    public void removeUser(String username) {
        users.removeIf(u -> u.getUsername().equals(username));
        saveToCSV();
    }

    private void loadUsers() {
        String path = "data/users.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String role = values[0];
                String username = values[1];
                String password = values[2];
                switch (role) {
                    case "Admin":
                        users.add(new Admin(username, password));
                        break;
                    case "BranchManager":
                        users.add(new BranchManager(username, password));
                        break;
                    case "Employee":
                        users.add(new Employee(username, password));
                        break;
                    case "CustomerTable":
                        users.add(new CustomerTable(username, password));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToCSV() {
        String path = "data/users.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (User user : users) {
                String line = String.join(",", user.getRole(), user.getUsername(), user.getPassword());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
}

