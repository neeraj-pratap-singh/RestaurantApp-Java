# RestaurantApp-Java
Management of Orders and Collections for Quick-Bites Restaurant

Basic layout
restaurant-app/
|-- src/
|   |-- Main.java
|   |-- authentication/
|   |-- menu/
|   |-- orders/
|   |-- tables/
|   |-- billing/
|   |-- reporting/
|   |-- utils/
|-- data/
|   |-- users.csv
|   |-- menu.csv
|   |-- orders.csv
|   |-- tables.csv

auth layout
restaurant-app/
|-- src/
|   |-- Main.java
|   |-- authentication/
|       |-- User.java
|       |-- Admin.java
|       |-- BranchManager.java
|       |-- Employee.java
|       |-- CustomerTable.java
|       |-- Authenticatable.java
|       |-- AuthenticationManager.java
|-- data/
|   |-- users.csv

menu layout
restaurant-app/
|-- src/
|   |-- menu/
|       |-- FoodItem.java
|       |-- MenuManager.java
|-- data/
|   |-- menu.csv

order layout
restaurant-app/
|-- src/
|   |-- order/
|       |-- Order.java
|       |-- OrderManager.java
|-- data/
|   |-- orders.csv