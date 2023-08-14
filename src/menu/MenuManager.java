package menu;

import java.util.*;
import java.io.*;

public class MenuManager {
    private List<FoodItem> menuItems = new ArrayList<>();
    
    public MenuManager() {
        loadMenu();
    }

    public void addFoodItem(FoodItem item) {
        menuItems.add(item);
        saveMenu();
    }

    public void removeFoodItem(String name) {
        menuItems.removeIf(item -> item.getName().equals(name));
        saveMenu();
    }

    public void updateFoodItem(FoodItem updatedItem) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getName().equals(updatedItem.getName())) {
                menuItems.set(i, updatedItem);
                saveMenu();
                return;
            }
        }
    }

    public List<FoodItem> getMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }

    private void loadMenu() {
        String path = "data/menu.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                FoodItem item = new FoodItem(parts[0], Double.parseDouble(parts[1]), parts[2]);
                menuItems.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMenu() {
        String path = "data/menu.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (FoodItem item : menuItems) {
                String line = item.getName() + "," + item.getPrice() + "," + item.getCategory();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
