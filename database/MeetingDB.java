package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MeetingDB extends Database {
    private static MeetingDB meetingDB;
    private static EmployeeDB employeeDB;
    private int numOfFields = 6;

    private MeetingDB() throws Exception{
        super();
        employeeDB = EmployeeDB.getInstance();
    }

    public static MeetingDB getInstance() throws Exception{
        if(meetingDB == null){return new MeetingDB();}
        return meetingDB;
    }

    protected PreparedStatement createFields() throws Exception {
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "meeting(meeting_id VARCHAR(30), room_id VARCHAR(10), start_time VARCHAR(5), end_time VARCHAR(5), owner_id VARCHAR(10), attendee_id VARCHAR(30), PRIMARY KEY(meeting_id))");
        return create;
    }

    public void insertRecord(String meeting_id, String room_id, String start, String end, String employee_id, String attending) throws Exception{
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO meeting(meeting_id VARCHAR(30), room_id VARCHAR(10), start_time VARCHAR(5), end_time VARCHAR(5), owner_id VARCHAR(10)), attendee_id VARCHAR(30)" +
                "VALUES('"+meeting_id+"', '"+room_id+"', '"+start+"', '"+end+"','"+employee_id+"', '"+attending+"')");
        insert.executeUpdate();
    }

    public void deleteRecord(String id) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("DELETE FROM meeting WHERE meeting_id=id");
        insert.executeUpdate();
    }

    public void updateRecord(String field, String updateThis, String id_field, String id) throws Exception{
        String statement = String.format("UPDATE meeting SET '%s'='%s' WHERE '%s'='%s'", field, updateThis, id_field, id);
        PreparedStatement update = super.getDatabase().prepareStatement(statement);
        update.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM meeting");
        return super.getAll(retrieve);
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM meeting WHERE employee_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("meeting_id");
        array[1] = retrieved.getString("room_id");
        array[2] = retrieved.getString("start_time");
        array[3] = retrieved.getString("end_time");
        array[4] = retrieved.getString("owner_id");
        array[5] = retrieved.getString("attending");

        if(super.isEmpty(array)){return null;}
        return array;
    }

    public ArrayList<String[]> getMeetingsFor(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM meeting WHERE employee_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[3];
        ArrayList<String[]> currentMeetings = new ArrayList<>();

        while(retrieved.next()){
            array[0] = retrieved.getString("meeting_id");
            array[1] = retrieved.getString("start_time");
            array[2] = retrieved.getString("end_time");
            currentMeetings.add(array);
        }
        if(currentMeetings.isEmpty()){return null;}
        return currentMeetings;
    }

    public ArrayList<String[]> getAttendeesFor(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM meeting WHERE meeting_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        ArrayList<String> attendees = new ArrayList<>();
        ArrayList<String[]> attendeeNames = new ArrayList<>();

        while(retrieved.next()){
            attendees.add(retrieved.getString("attendee_id"));
        }
        if(attendees.isEmpty()){return null;}

        for(int i = 0; i < attendees.size(); i++){
            String[] idAndName = employeeDB.getThis(attendees.get(i));
            attendeeNames.add(idAndName);
        }
        return attendeeNames;
    }
}
