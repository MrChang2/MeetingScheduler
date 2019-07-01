package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleDB extends Database {
    private int numOfFields = 3;

    public ScheduleDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields() throws Exception {
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "schedule(employee_id VARCHAR(10), start_hour VARCHAR(5), end_hour VARCHAR(5), PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertRecord(String employee_id, String start_hour, String end_hour) throws Exception{
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO schedule(id VARCHAR(10), start_hour VARCHAR(5), end_hour VARCHAR(5), " +
                "VALUES('"+employee_id+"', '"+start_hour+"', '"+end_hour+"')");
        insert.executeUpdate();
    }

    public void deleteRecord(Connection database, String employee_id) throws Exception {
        PreparedStatement insert = database.prepareStatement("DELETE FROM schedule WHERE id=employee_id");
        insert.executeUpdate();
    }

    public void updateRecord(String field, String updateThis, String id) throws Exception{
        String statement = String.format("UPDATE schedule SET '%s'='%s' WHERE employee_id='%s'", field, updateThis, id);
        PreparedStatement update = super.getDatabase().prepareStatement(statement);
        update.executeUpdate();
    }

    public HashMap<String, String[]> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM schedule");
        ResultSet retrieved = retrieve.executeQuery();

        HashMap<String, String[]> retrievedInfo = new HashMap<>();
        String[] times = new String[2];

        while(retrieved.next()){
            times[0] = retrieved.getString("start_hour");
            times[1] = retrieved.getString("end_hour");
            retrievedInfo.put(retrieved.getString("employee_id"), times);
        }
        return retrievedInfo;
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM schedule WHERE employee_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("employee_id");
        array[1] = retrieved.getString("start_hour");
        array[2] = retrieved.getString("end_hour");
        return array;
    }
}
