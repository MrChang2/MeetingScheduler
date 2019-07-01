import java.util.ArrayList;
import java.util.Scanner;
public class Administrator extends Employee{
    private Employee_Database employeeDatabase;
    private Room_Database roomDatabase;
    public Administrator(String e, String f, String l, String u, String pa, String po, WorkHours_Database wh) {
        super(e, f, l, u, pa, po, wh);
        this.setAdmin(true);
    }

    public Employee_Database getEmployeeDatabase() {
        return employeeDatabase;
    }

    public void setEmployeeDatabase(Employee_Database employeeDatabase) {
        this.employeeDatabase = employeeDatabase;
    }

    public Room_Database getRoomDatabase() {
        return roomDatabase;
    }

    public void setRoomDatabase(Room_Database roomDatabase) {
        this.roomDatabase = roomDatabase;
    }

    //***************GUI BUTTON*************************
    //Admins can add new Employees to database
    public void addEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username of the new Employee: ");
        String username = sc.next();
        System.out.println("Set the password for the new user: ");
        String password = sc.next();
        Employee newEmployee = new Employee(username, password);
        //Check to see if username already exists
        for (int x=0;x<employeeDatabase.getEmployeeDatabase().size();x++) {
            if (employeeDatabase.getEmployeeDatabase().get(x).getUsername().equals(username)) {
                System.out.println(username + " already exists.");
                break;
            }
            if (x==employeeDatabase.getEmployeeDatabase().size()-1) {
                employeeDatabase.getEmployeeDatabase().add(newEmployee);
                System.out.println(username + " has been added to the database.");
            }
        }
    }
    
    //***************GUI BUTTON*************************
    //Admins can delete Employees
    public void deleteEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username of the Employee to delete: ");
        String username = sc.next();
        //Searches for Employee and removes it
        for (int x=0;x<employeeDatabase.getEmployeeDatabase().size();x++) {
            if (employeeDatabase.getEmployeeDatabase().get(x).getUsername().equals(username)) {
                employeeDatabase.getEmployeeDatabase().remove(x);
                System.out.println(username + " has been removed.");
                break;
            }
            if (x==employeeDatabase.getEmployeeDatabase().size()-1) {
                System.out.println("Employee does not exist.");
            }
        }
    }
    public void updateEmployee() {

    }
    
    //***************GUI BUTTON*************************
    public void addRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number for the room: ");
        String roomID = sc.next();
        Room room = new Room(roomID);
        for (int x=0;x<roomDatabase.getRooms().size();x++) {
            if (roomDatabase.getRooms().get(x).getRoomID().equals(roomID)) {
                System.out.println("Room already exists.");
                break;
            }
            if (x==roomDatabase.getRooms().size()-1) {
                roomDatabase.getRooms().add(room);
                System.out.println("Added new room " + roomID);
            }
        }
    }
    
    //***************GUI BUTTON*************************
    public void deleteRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number for the room to remove: ");
        String roomID = sc.next();
        for (int x=0;x<roomDatabase.getRooms().size();x++) {
            if (roomDatabase.getRooms().get(x).getRoomID().equals(roomID)) {
                roomDatabase.getRooms().remove(x);
                System.out.println("Removed room " + roomID);
                break;
            }
            if (x==roomDatabase.getRooms().size()-1) {
                System.out.println("Room does not exist.");
            }
        }
    }
    public Employee getEmployee(Employee e) {
        ArrayList<Employee> database = employeeDatabase.getEmployeeDatabase();
        return database.get(database.indexOf(e));
    }

    public void viewAllEmployeeInfo() {
        employeeDatabase.viewAllInfo();
    }
    public void viewAllRoomInfo() {
        roomDatabase.viewAllRooms();
    }
    //Allows administrator to give another employee administrator privileges
    public void makeAdmin(String id) {
        for (int x=0;x<employeeDatabase.getEmployeeDatabase().size();x++) {
            if (employeeDatabase.getEmployeeDatabase().get(x).getEmployeeID().equals(id)) {
                //employeeDatabase.getEmployeeDatabase().set(x, new Administrator(employeeDatabase.getEmployeeDatabase().get(x)));
            }
        }
    }
    
    //***************GUI BUTTON*************************
    public void resetEmployeePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID of the Employee: ");
        String employeeID = sc.next();
        for (int x=0;x<employeeDatabase.getEmployeeDatabase().size();x++) {
            if (employeeDatabase.getEmployeeDatabase().get(x).getEmployeeID().equals(employeeID)) {
                System.out.println("Enter new password: ");
                String password = sc.next();
                employeeDatabase.getEmployeeDatabase().get(x).setPassword(password);
                System.out.println("Password for " + employeeDatabase.getEmployeeDatabase().get(x).getUsername() + " has been changed.");
                break;
            }
            if (x==employeeDatabase.getEmployeeDatabase().size()-1) {
                System.out.println("Invalid ID.");
            }
        }
    }
}
