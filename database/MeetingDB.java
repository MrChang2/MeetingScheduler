package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MeetingDB extends Database {
    private int numOfFields = 4;

    public MeetingDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields() throws Exception {
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "meeting(meeting_id VARCHAR(30), room_id VARCHAR(10), employee_id VARCHAR(10), attending meeting_id VARCHAR(30), PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertRecord(String meeting_id, String room_id, String employee_id, String attending) throws Exception{
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO meeting(meeting_id VARCHAR(30), room_id VARCHAR(10), employee_id VARCHAR(10)), attending meeting_id VARCHAR(30)" +
                "VALUES('"+meeting_id+"', '"+room_id+"', '"+employee_id+"', '"+attending+"')");
        insert.executeUpdate();
    }

    public void deleteRecord(String id) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("DELETE FROM meeting WHERE meeting_id=id");
        insert.executeUpdate();
    }

    public void updateRecord(String field, String updateThis, String id) throws Exception{
        String statement = String.format("UPDATE meeting SET '%s'='%s' WHERE employee_id='%s'", field, updateThis, id);
        PreparedStatement update = super.getDatabase().prepareStatement(statement);
        update.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM meeting");
        ResultSet retrieved = retrieve.executeQuery();

        ArrayList<ResultSet> retrievedInfo = new ArrayList<ResultSet>();
        while(retrieved.next()){
            retrievedInfo.add(retrieved);
        }
        return retrievedInfo;
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM meeting WHERE employee_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("meeting_id");
        array[1] = retrieved.getString("room_id");
        array[2] = retrieved.getString("employee_id");
        array[3] = retrieved.getString("attending");
        return array;
    }
}
