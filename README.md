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

authentication layout
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

Employee Management Module
restaurant-app/
|-- src/
|   |-- employee/
|       |-- Employee.java
|       |-- EmployeeManager.java
|-- data/
|   |-- employees.csv

Branch Management Module
restaurant-app/
|-- src/
|   |-- branch/
|       |-- Branch.java
|       |-- BranchManager.java
|-- data/
|   |-- branches.csv

Customer Interaction Module
restaurant-app/
|-- src/
|   |-- customer/
|       |-- Table.java
|       |-- CustomerInteractionManager.java
|       |-- Menu.java
|-- data/
|   |-- menu.csv

Order Management Module
restaurant-app/
|-- src/
|   |-- order/
|       |-- Order.java
|       |-- OrderItem.java
|       |-- OrderManager.java
|-- data/
|   |-- orders.csv

Reporting & Analytics Module
restaurant-app/
|-- src/
|   |-- analytics/
|       |-- ReportGenerator.java
|       |-- AnalyticsManager.java
|-- data/
|   |-- reports/
