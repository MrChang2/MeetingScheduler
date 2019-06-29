package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReminderDB extends Database{
    private int numOfFields = 4;

    public ReminderDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields(Connection database) throws Exception {
        PreparedStatement create = database.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "reminder(reminder_id VARCHAR(10), employee_id VARCHAR(10), meeting_id VARCHAR(10), accepted TINYINT(1), PRIMARY KEY(reminder_id))");
        return create;
    }

    public void insertFields(Connection database, String reminder_id, String employee_id, String meeting_id, int accepted) throws Exception{
        PreparedStatement insert = database.prepareStatement("INSERT INTO reminder(reminder_id VARCHAR(10), employee_id VARCHAR(10), meeting_id VARCHAR(10), accepted TINYINT(1))" +
                "VALUES('"+reminder_id+"', '"+employee_id+"',''"+meeting_id+"','"+accepted+"')'");
        insert.executeUpdate();
    }

    public void deleteFields(Connection database, String id) throws Exception {
        PreparedStatement insert = database.prepareStatement("DELETE FROM reminder WHERE reminder_id=id");
        insert.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM reminder");
        ResultSet retrieved = retrieve.executeQuery();

        ArrayList<ResultSet> retrievedInfo = new ArrayList<ResultSet>();
        while(retrieved.next()){
            retrievedInfo.add(retrieved);
        }
        return retrievedInfo;
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM reminder WHERE reminder_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("reminder_id");
        array[1] = retrieved.getString("employee_id");
        array[2] = retrieved.getString("meeting_id");
        array[3] = retrieved.getString("accepted");
        return array;
    }
}
