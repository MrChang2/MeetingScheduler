import java.util.ArrayList;
public class Administrator extends Employee{
    private Employee_Database employeeDatabase;
    private Room_Database roomDatabase;
    public Administrator(String e, String f, String l, String u, String pa, String po, String as, String ae) {
        super(e, f, l, u, pa, po, as, ae);
        this.setAdmin(true);
    }
    public Administrator(Employee e) {
        super(e.getEmployeeID(), e.getFirst_name(), e.getLast_name(), e.getUsername(), e.getPassword(), e.getPosition(), e.getAvailableStart(), e.getAvailableEnd());
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
    //Allows administrator to give another employee administrator privileges
    public void makeAdmin(String id) {
        for (int x=0;x<employeeDatabase.getEmployeeDatabase().size();x++) {
            if (employeeDatabase.getEmployeeDatabase().get(x).getEmployeeID().equals(id)) {
                employeeDatabase.getEmployeeDatabase().set(x, new Administrator(employeeDatabase.getEmployeeDatabase().get(x)));
            }
        }
    }
}
