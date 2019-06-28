import java.util.ArrayList;
import java.util.Scanner;
public class Login {
    private Employee_Database employees;
    public Login(Employee_Database ed) {
        employees = ed;
    }

    public Employee LoginCheck() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> database = employees.getEmployeeDatabase();
        String usernameInput;
        String passwordInput;
        Employee user = null;

        System.out.println("Enter username: ");
        usernameInput = sc.next();
        System.out.println("Enter password: ");
        passwordInput = sc.next();

        for (int x=0;x<database.size();x++) {
            if (usernameInput.equals(database.get(x).getUsername()) && passwordInput.equals(database.get(x).getPassword())) {
                user = database.get(x);
            }
        }
        if (user==null) {
            System.out.println("Invalid Login Info");
        }
        return user;
    }
}
