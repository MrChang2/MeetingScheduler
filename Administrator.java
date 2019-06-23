import java.util.ArrayList;
public class Administrator {
    private Employee_Database employeeDatabase;
    private Room_Database roomDatabase;
    public Administrator(Employee_Database d) {
        employeeDatabase = d;
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
}
