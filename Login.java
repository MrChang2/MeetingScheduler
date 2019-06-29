import java.util.ArrayList;
import java.util.Scanner;
public class Login {
    private Employee_Database employees;
    private Notification_Database notifications;
    public Login(Employee_Database ed, Notification_Database n) {
        employees = ed;
        notifications = n;
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
        else {
            for (int x=0;x<notifications.getNotificationDatabase().size();x++) {
                if (notifications.getNotificationDatabase().get(x).getEmployeeID().equals(user.getEmployeeID())) {
                    System.out.println("New Notification: " + notifications.getNotificationDatabase().get(x).getMessage());
                    notifications.getNotificationDatabase().remove(x);
                    x--;
                }
            }
        }
        return user;
    }
}
