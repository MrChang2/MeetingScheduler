import database.EmployeeDB;
import database.LoginDB;
import database.ReminderDB;

import java.util.Scanner;

public class Login {
    private LoginDB loginDB;
    private EmployeeDB employeeDB;
    private ReminderDB notifications;

    public Login() throws Exception{
        loginDB = LoginDB.getInstance();
        employeeDB = EmployeeDB.getInstance();
        notifications = ReminderDB.getInstance();
    }

    public String LoginCheck() throws Exception{
        Scanner sc = new Scanner(System.in);
        String usernameInput;
        String passwordInput;
        String user = null;

        System.out.println("Enter username: ");
        usernameInput = sc.next();
        System.out.println("Enter password: ");
        passwordInput = sc.next();

        String[] credentials = loginDB.getThis(usernameInput);
        if(credentials[1] == usernameInput && credentials[2] == passwordInput){return credentials[0];}
        return null;
    }
}
