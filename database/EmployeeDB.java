package database;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import database.ScheduleDB;

public class EmployeeDB extends Database {
    private int numOfFields = 5;
    private ScheduleDB scheduleDB;

    public EmployeeDB() throws Exception{
        super();
        scheduleDB = new ScheduleDB();
    }

    protected PreparedStatement createFields() throws Exception{
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "employee(employee_id VARCHAR(10), first_name VARCHAR(50), last_name VARCHAR(50)," +
                "position VARCHAR(50), admin TINYBIT(1) PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertRecord(String id, String first_name, String last_name, String position, boolean admin) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO employee(employee_id VARCHAR(10), first_name VARCHAR(50), last_name VARCHAR(50)," +
                "position VARCHAR(50), admin TINYBIT(1)) VALUES('"+id+"', '"+first_name+"', '"+last_name+"', '"+position+"', '"+admin+"')");
        insert.executeUpdate();
    }

    public void deleteRecord(String id) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("DELETE FROM employee WHERE employee_id=id");
        insert.executeUpdate();
    }

    public void updateRecord(String field, String updateThis, String id) throws Exception{
        String statement = String.format("UPDATE employee SET '%s'='%s' WHERE employee_id='%s'", field, updateThis, id);
        PreparedStatement update = super.getDatabase().prepareStatement(statement);
        update.executeUpdate();
    }

    public void updateRecord(boolean is_admin, String id) throws Exception{
        PreparedStatement update = super.getDatabase().prepareStatement("UPDATE employee SET admin=is_admin WHERE employee_id=id");
        update.executeUpdate();
    }

    public HashMap getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM employee");
        ResultSet retrieved = retrieve.executeQuery();

        HashMap<String, String> idAndName = new HashMap();
        while(retrieved.next()){

            idAndName.put(retrieved.getString("employee_id"),
                    retrieved.getString("first_name") + " " +
                            retrieved.getString("last_name"));
        }
        return idAndName;
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM employee WHERE employee_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("employee_id");
        array[1] = retrieved.getString("first_name");
        array[2] = retrieved.getString("last_name");
        array[3] = retrieved.getString("position");
        array[4] = retrieved.getString("admin");
        return array;
    }

    public ArrayList<String> getValidEmployees(int hour, int minutes) throws Exception {
        HashMap<String, String> allEmployees = getAll();
        HashMap<String, String[]> schedule = scheduleDB.getAll();
        ArrayList<String> availableEmployees = new ArrayList<>();

        for(Map.Entry<String, String> employee : allEmployees.entrySet()){

        }
        return;
    }

    public void checkSchedule(HashMap<String, String> allEmployees, HashMap<String, String[]> schedule, ArrayList<String> availableEmployees, Map.Entry employee, int hour, int minutes) throws Exception{
        for(Map.Entry<String, String> employeeSchedule : allEmployees.entrySet()){
            if(employee.getKey().equals(employeeSchedule.getKey())){
                String[] times = schedule.get(employeeSchedule.getKey());
                int start = Integer.parseInt(times[0]);
                int end = Integer.parseInt(times[1]);

                int

                return;
            }

        }
    }
}
