package employee;

import java.util.*;
import java.io.*;

public class EmployeeManager {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeManager() {
        loadEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployees();
    }

    public void updateEmployee(Employee employee) {
        // TODO: Implement updating an existing employee
        saveEmployees();
    }

    public void removeEmployee(String id) {
        employees.removeIf(emp -> emp.getId().equals(id));
        saveEmployees();
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    private void loadEmployees() {
        // TODO: Implement loading employees from CSV
    }

    private void saveEmployees() {
        // TODO: Implement saving employees to CSV
    }
}
