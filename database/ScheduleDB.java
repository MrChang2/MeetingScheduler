package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScheduleDB extends Database {
    private int numOfFields = 3;

    public ScheduleDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields(Connection database) throws Exception {
        PreparedStatement create = database.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "schedule(employee_id VARCHAR(10), start_hour int, end_hour int, PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertFields(Connection database, String employee_id, String start_hour, String end_hour) throws Exception{
        PreparedStatement insert = database.prepareStatement("INSERT INTO schedule(id VARCHAR(10), start_hour VARCHAR(10), end_hour VARCHAR(10), " +
                "VALUES('"+employee_id+"', '"+start_hour+"', '"+end_hour+"')");
        insert.executeUpdate();
    }

    public void deleteFields(Connection database, String employee_id) throws Exception {
        PreparedStatement insert = database.prepareStatement("DELETE FROM schedule WHERE id=employee_id");
        insert.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM schedule");
        ResultSet retrieved = retrieve.executeQuery();

        ArrayList<ResultSet> retrievedInfo = new ArrayList<ResultSet>();
        while(retrieved.next()){
            retrievedInfo.add(retrieved);
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
