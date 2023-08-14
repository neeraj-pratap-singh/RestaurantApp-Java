package auth;

import java.util.*;
import java.io.*;

public class AuthenticationManager {
    private List<User> users = new ArrayList<>();

    public AuthenticationManager() {
        loadUsers();
    }

    public boolean authenticate(String username, String password) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    public User getUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    private void loadUsers() {
        // TODO: Implement loading users from CSV
    }
}
