import java.util.ArrayList;
public class Administrator {
    private Employee_Database employeeDatabase;
    private Room_Database roomDatabase;
    public Administrator(Employee_Database d, Room_Database r) {
        employeeDatabase = d;
        roomDatabase = r;
    }

    public Employee_Database getEmployeeDatabase() {
        return employeeDatabase;
    }

    public Room_Database getRoomDatabase() {
        return roomDatabase;
    }

    public void addEmployee(Employee e) {
        employeeDatabase.getEmployeeDatabase().add(e);
    }
    public void deleteEmployee(Employee e) {
        employeeDatabase.getEmployeeDatabase().remove(e);
    }
    public void updateEmployee() {

    }
    public void addRoom(Room r) {
        roomDatabase.getRooms().add(r);
    }
    public void deleteRoom(Room r) {
        roomDatabase.getRooms().remove(r);
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
}
