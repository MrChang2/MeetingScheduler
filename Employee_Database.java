import java.util.ArrayList;
public class Employee_Database {
    private ArrayList<Employee> EmployeeDatabase;

    public Employee_Database() {
        EmployeeDatabase = new ArrayList<Employee>();
    }
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
        if (selected.isAdmin()) {
            info = info + "Is an admin" + "\n";
        }
        else {
            info = info + "Not and admin" + "\n";
        }

        return info;
    }
    public void viewAllInfo() {
        ArrayList<Employee> ed = this.getEmployeeDatabase();
        for (int x=0;x<ed.size();x++) {
            System.out.println(this.viewInfo(ed.get(x)));
        }
    }
}
