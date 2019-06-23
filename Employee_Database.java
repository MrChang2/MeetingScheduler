import java.util.ArrayList;
public class Employee_Database {
    private ArrayList<Employee> EmployeeDatabase;

    public ArrayList<Employee> getEmployeeDatabase() {
        return EmployeeDatabase;
    }

    public String viewInfo(Employee e) {
        Employee selected = EmployeeDatabase.get(EmployeeDatabase.indexOf(e));
        String info = "Employee ID: " + selected.getEmployeeID() + "\n";
        info = info + "First Name: " + selected.getFirst_name() + "\n";
        info = info + "Last Name: " + selected.getLast_name() + "\n";
        info = info + "Username: " + selected.getUsername() + "\n";
        info = info + "Password: " + selected.getPassword() + "\n";
        info = info + "Position: " + selected.getPosition() + "\n";

        return info;
    }
}
