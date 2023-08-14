package customer;

import java.util.*;
import java.io.*;

public class Menu {
    private List<MenuItem> items;

    public Menu() {
        loadMenu();
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    private void loadMenu() {
        // TODO: Implement loading menu items from CSV
    }
}
