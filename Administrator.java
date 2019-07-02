import database.RoomDB;
import database.EmployeeDB;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrator extends Employee{
    private EmployeeDB employeeDB;
    private RoomDB roomDB;
    public Administrator(String id, String first, String last, String user, String pass, String pos, String start, String end) throws Exception{
        super(id, first, last, user, pass, pos, start, end);
        this.setAdmin(true);
    }
    public Administrator(Employee e) throws Exception{
        super(e.getEmployeeID(), e.getFirst_name(), e.getLast_name(), e.getUsername(), e.getPassword(), e.getPosition(), e.getAvailableStart(), e.getAvailableEnd());
        this.setAdmin(true);
    }

    public void getEmployeeDatabase() throws Exception{
        employeeDB = EmployeeDB.getInstance();
    }

    public void getRoomDatabase() throws Exception{
        roomDB = RoomDB.getInstance();
    }

    //Admins can add new Employees to database
    public void addEmployee(String id, String first_name, String last_name, String position, boolean admin) throws Exception{
        employeeDB.insertRecord(id, first_name, last_name, position, admin);
    }

    //Admins can delete Employees
    public void deleteEmployee(String id) throws Exception{
        employeeDB.deleteRecord(id);
    }

    public void updateEmployee(String field, String updateThis, String id) throws Exception{
        employeeDB.updateRecord(field, updateThis, id);
    }

    public void addRoom(String room_id, int occupied) throws Exception {
        roomDB.insertRecord(room_id, occupied);
    }
    public void deleteRoom(String room_id) throws Exception {
        roomDB.deleteRecord(room_id);
    }
    public String[] getEmployee(String id) throws Exception{
        return employeeDB.getThis(id);
    }

    public HashMap<String, String> viewAllEmployeeInfo() throws Exception{
        return employeeDB.getAll();
    }
    public ArrayList<String> viewAllRoomInfo() throws Exception{
        return roomDB.getAll();
    }
    //Allows administrator to give another employee administrator privileges
    public void makeAdmin(String id) throws Exception{
        employeeDB.updateRecord(true, id);
    }
}
