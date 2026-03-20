// Employee class
class Employee implements Comparable<Employee> {
    int id;
    String name;
    String position;

    public Employee(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " (" + name + ", " + position + ")";
    }
}

public class CompanyEmployeeBST {
    public static void main(String[] args) {
        BinaryTree<Employee> employees = new BinaryTree<>();

        // Add employees
        employees.add(new Employee(1005, "Alice", "Engineer"));
        employees.add(new Employee(1010, "Bob", "Manager"));
        employees.add(new Employee(1001, "Charlie", "Intern"));
        employees.add(new Employee(1007, "Diana", "Engineer"));
        employees.add(new Employee(1015, "Ethan", "Director"));
        employees.add(new Employee(1003, "Fiona", "Analyst"));
        employees.add(new Employee(1012, "George", "Engineer"));

        System.out.println("Inorder traversal (sorted by ID):");
        employees.inOrder(employees.getHead());
        System.out.println("\n");

        System.out.println("Preorder traversal (reporting/hierarchy view):");
        employees.preOrder(employees.getHead());
        System.out.println("\n");

        System.out.println("Postorder traversal (cleanup/shutdown view):");
        employees.postOrder(employees.getHead());
        System.out.println("\n");

        // Searching
        Employee searchEmp = new Employee(1007, "", "");
        if (employees.contains(searchEmp)) {
            System.out.println("Employee with ID " + searchEmp.id + " exists in the company.");
        } else {
            System.out.println("Employee with ID " + searchEmp.id + " not found.");
        }

        // Removing an employee
        System.out.println("Removing employee with ID 1010 (Bob).");
        employees.remove(new Employee(1010, "", ""));

        System.out.println("Inorder traversal after removal:");
        employees.inOrder(employees.getHead());
    }
}
