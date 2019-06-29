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

    protected PreparedStatement createFields(Connection database) throws Exception {
        PreparedStatement create = database.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "meeting(meeting_id VARCHAR(10), room_id VARCHAR(10), employee_id VARCHAR(10), attending TINYINT(1), PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertFields(Connection database, String meeting_id, String room_id, String employee_id, int attending) throws Exception{
        PreparedStatement insert = database.prepareStatement("INSERT INTO meeting(meeting_id VARCHAR(10), room_id VARCHAR(10), employee_id VARCHAR(10)), attending TINYINT(1)" +
                "VALUES('"+meeting_id+"', '"+room_id+"', '"+employee_id+"', '"+attending+"')");
        insert.executeUpdate();
    }

    public void deleteFields(Connection database, String id) throws Exception {
        PreparedStatement insert = database.prepareStatement("DELETE FROM meeting WHERE meeting_id=id");
        insert.executeUpdate();
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
