class Employee {
    private final int employeeId;
    private final String name;
    private final String position;
    private final double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name=%s, position=%s, salary=%.2f}", employeeId, name, position, salary);
    }
}

public class EmployeeManagementSystem {
    private final Employee[] employees;
    private int count;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    public boolean addEmployee(Employee employee) {
        if (count >= employees.length || employee == null) {
            return false;
        }
        employees[count++] = employee;
        return true;
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem(5);
        system.addEmployee(new Employee(101, "Alice", "Developer", 72000));
        system.addEmployee(new Employee(102, "Bob", "Designer", 65000));
        system.addEmployee(new Employee(103, "Cathy", "Manager", 90000));

        System.out.println("All employees:");
        system.traverseEmployees();

        System.out.println("\nSearch employee 102:");
        System.out.println(system.searchEmployee(102));

        system.deleteEmployee(101);
        System.out.println("\nAfter deleting employee 101:");
        system.traverseEmployees();
    }
}
