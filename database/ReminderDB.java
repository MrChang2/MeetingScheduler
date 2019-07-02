package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReminderDB extends Database{
    private static ReminderDB reminderDB;
    private int numOfFields = 4;

    private ReminderDB() throws Exception{
        super();
    }

    public static ReminderDB getInstance() throws Exception{
        if(reminderDB == null){return new ReminderDB();}
        return reminderDB;
    }

    protected PreparedStatement createFields() throws Exception {
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "reminder(reminder_id VARCHAR(10), employee_id VARCHAR(10), meeting_id VARCHAR(10), accepted TINYINT(1), PRIMARY KEY(reminder_id))");
        return create;
    }

    public void insertRecord(String reminder_id, String employee_id, String meeting_id, int accepted) throws Exception{
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO reminder(reminder_id VARCHAR(10), employee_id VARCHAR(10), meeting_id VARCHAR(10), accepted TINYINT(1))" +
                "VALUES('"+reminder_id+"', '"+employee_id+"',''"+meeting_id+"','"+accepted+"')'");
        insert.executeUpdate();
    }

    public void deleteRecord(String id) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("DELETE FROM reminder WHERE reminder_id=id");
        insert.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM reminder");
        return super.getAll(retrieve);
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
        if(super.isEmpty(array)){return null;}
        return array;
    }
}
