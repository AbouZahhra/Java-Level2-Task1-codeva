import java.util.ArrayList;
import java.io.*;

public class EmployeeService {

    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
        System.out.println("Employee added successfully!");
    }

    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee emp : employees) {
            emp.display();
        }
    }

    public Employee searchEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public void updateEmployee(int id, String name, double salary, String dept) {
        Employee emp = searchEmployee(id);

        if (emp != null) {
            emp.setName(name);
            emp.setSalary(salary);
            emp.setDepartment(dept);
            System.out.println("Employee updated.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void deleteEmployee(int id) {
        Employee emp = searchEmployee(id);

        if (emp != null) {
            employees.remove(emp);
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("employees.txt"))) {

            for (Employee emp : employees) {
                pw.println(emp.getId() + "," +
                           emp.getName() + "," +
                           emp.getSalary() + "," +
                           emp.getDepartment());
            }

        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File("employees.txt");

        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double salary = Double.parseDouble(data[2]);
                String dept = data[3];

                employees.add(new Employee(id, name, salary, dept));
            }

        } catch (Exception e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }
}