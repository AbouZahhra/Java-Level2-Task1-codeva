import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        // Load data 
        service.loadFromFile();

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // important fix

            switch (choice) {
                case 1:
                    int id;
                    while (true) {
                    System.out.print("Enter ID: ");
                    id = sc.nextInt();
                    if (service.searchEmployee(id) != null) {
                 System.out.println(" ID already exists! Try another one.");
                    } else {
                     break;
                    }
                        }

                            sc.nextLine();

                            System.out.print("Enter Name: ");
                            String name = sc.nextLine();

                            System.out.print("Enter Salary: ");
                            double salary = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Enter Department: ");
                            String dept = sc.nextLine();

                            service.addEmployee(new Employee(id, name, salary, dept));
                            break;

                

                case 2:
                    service.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int searchId = sc.nextInt();

                    Employee emp = service.searchEmployee(searchId);
                    if (emp != null) {
                        System.out.println("Employee Found:");
                        emp.display();
                    } else {
                        System.out.println("Not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("New Salary: ");
                    double newSalary = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("New Department: ");
                    String newDept = sc.nextLine();

                    service.updateEmployee(updateId, newName, newSalary, newDept);
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    int deleteId = sc.nextInt();

                    service.deleteEmployee(deleteId);
                    break;

                case 6:
                    // Save data
                    service.saveToFile();

                    System.out.println("Goodbye ");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}