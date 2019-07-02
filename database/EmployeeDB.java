package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDB extends Database {
    private static EmployeeDB employeeDB;
    private ScheduleDB scheduleDB;
    private MeetingDB meetingDB;
    private int numOfFields = 5;

    private EmployeeDB() throws Exception{
        super();
        scheduleDB = ScheduleDB.getInstance();
        meetingDB = MeetingDB.getInstance();
    }

    public static EmployeeDB getInstance() throws Exception {
        if(employeeDB == null){return new EmployeeDB();}
        return employeeDB;
    }

    protected PreparedStatement createFields() throws Exception{
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "employee(employee_id VARCHAR(10), first_name VARCHAR(50), last_name VARCHAR(50)," +
                "position VARCHAR(50), admin TINYBIT(1) PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertRecord(String id, String first_name, String last_name, String position, boolean admin) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO employee(employee_id VARCHAR(10), " +
                "first_name VARCHAR(50), last_name VARCHAR(50), position VARCHAR(50), admin TINYBIT(1)) " +
                "VALUES('"+id+"', '"+first_name+"', '"+last_name+"', '"+position+"', '"+admin+"')");
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
                    retrieved.getString("first_name") + "," +
                    retrieved.getString("last_name") + "," +
                    retrieved.getString("position") + "," +
                    retrieved.getString("admin"));
        }
        if(idAndName.isEmpty()){return null;}
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

        if(super.isEmpty(array)){return null;}
        return array;
    }

    public ArrayList<String> getValidEmployees(String start_meeting, String end_meeting) throws Exception {
        HashMap<String, String> allEmployees = getAll();
        HashMap<String, String[]> schedule = scheduleDB.getAll();
        ArrayList<String> availableEmployeeIDs = new ArrayList<>();
        ArrayList<String> availableEmployeeNames = new ArrayList<>();

        for(Map.Entry<String, String> employee : allEmployees.entrySet()){
            checkSchedule(allEmployees, schedule, availableEmployeeIDs, employee, start_meeting, end_meeting);
        }

        if(!availableEmployeeIDs.isEmpty()) {
            for (int i = 0; i < availableEmployeeIDs.size(); i++) {
                availableEmployeeNames.add(availableEmployeeIDs + " " +
                        getThis(availableEmployeeIDs.get(i))[1] + " " +
                        getThis(availableEmployeeIDs.get(i))[2]);
            }
        }
        else{
            return null;
        }
        return availableEmployeeNames;
    }

    public void checkSchedule(HashMap<String, String> allEmployees, HashMap<String, String[]> schedule,
                              ArrayList<String> availableEmployees, Map.Entry employee, String start_meeting,
                              String end_meeting) throws Exception{
        for(Map.Entry<String, String> employeeSchedule : allEmployees.entrySet()){
            if(employee.getKey().equals(employeeSchedule.getKey())){
                String[] times = schedule.get(employeeSchedule.getKey());
                if(checkTime(times, start_meeting, end_meeting, employee.getKey().toString())){
                    availableEmployees.add(employee.getKey().toString());
                }
                return;
            }
        }
    }

    public int[] timeStringToInt(String[] times){
        String[] start = times[0].split(":");
        String[] end = times[1].split(":");

        int start_hr = Integer.parseInt(start[0]);
        int start_min = Integer.parseInt(start[1]);

        int end_hr = Integer.parseInt(end[0]);
        int end_min = Integer.parseInt(end[1]);

        return new int[]{start_hr, start_min, end_hr, end_min};
    }

    public int[] timeStringToInt(String[] times, String start_meeting, String end_meeting){
        String[] start_work = times[0].split(":");
        String[] end_work = times[1].split(":");

        String[] start_meet = start_meeting.split(":");
        String[] end_meet = end_meeting.split(":");

        int start_work_hr = Integer.parseInt(start_work[0]);
        int start_work_min = Integer.parseInt(start_work[1]);

        int end_work_hr = Integer.parseInt(end_work[0]);
        int end_work_min = Integer.parseInt(end_work[1]);

        int start_meet_hr = Integer.parseInt(start_meet[0]);
        int start_meet_min = Integer.parseInt(start_meet[1]);

        int end_meet_hr = Integer.parseInt(end_meet[0]);
        int end_meet_min = Integer.parseInt(end_meet[1]);

        return new int[]{start_work_hr, start_work_min, end_work_hr, end_work_min,
                start_meet_hr, start_meet_min, end_meet_hr, end_meet_min};
    }

    public boolean checkTime(int[] current_meeting_times, int[] new_meeting_times){
        boolean available = false;

        int start_current_hr = current_meeting_times[0];
        int start_current_min = current_meeting_times[1];
        int end_current_hr = current_meeting_times[2];
        int end_current_min = current_meeting_times[3];

        int start_new_hr = new_meeting_times[0];
        int start_new_min = new_meeting_times[1];
        int end_new_hr = new_meeting_times[2];
        int end_new_min = new_meeting_times[3];

        if((start_new_hr < start_current_hr && end_new_hr < end_current_hr) ||
                (start_new_hr < start_current_hr && end_new_hr == start_current_hr && end_new_min < start_current_min)){
            available = true;
        }
        if((start_new_hr > end_current_hr) ||
                (start_new_hr >= end_current_hr && start_new_min > end_current_min)){
            available = true;
        }

        return available;
    }

    public boolean checkTime(String[] times, String start_meeting, String end_meeting, String employeeID) throws Exception{
        int[] processedTime = timeStringToInt(times, start_meeting, end_meeting);

        int start_work_hr = processedTime[0];
        int start_work_min = processedTime[1];
        int end_work_hr = processedTime[2];
        int end_work_min = processedTime[3];
        int start_meet_hr = processedTime[4];
        int start_meet_min = processedTime[5];
        int end_meet_hr = processedTime[6];
        int end_meet_min = processedTime[7];

        if(start_meet_hr >= start_work_hr && start_meet_min >= start_work_min &&
                end_meet_hr <= end_work_hr && end_meet_min <= end_work_min){
            if(checkCurrentMeetings(new int[] {start_meet_hr, start_meet_min, end_meet_hr, end_meet_min},
                    employeeID)){
                return true;
            }
        }
        return false;
    }

    public boolean checkCurrentMeetings(int[] check_target_times, String employeeID) throws Exception{
        ArrayList<String[]> currentMeetings= meetingDB.getMeetingsFor(employeeID);
        String[] currentMeetingTimes = new String[2];
        int[] currentMeetingSchedule;
        boolean available = false;

        if(currentMeetings.isEmpty()){return true;}

        for(int i = 0; i < currentMeetings.size(); i++){
            currentMeetingTimes[0] = currentMeetings.get(i)[1]; //start_meeting_time
            currentMeetingTimes[1] = currentMeetings.get(i)[2]; //end_meeting_time
            currentMeetingSchedule = timeStringToInt(currentMeetingTimes);
            if(checkTime(currentMeetingSchedule, check_target_times)){
                available = true;
            }
            else{
                available = false;
            }
        }

        return available;
    }

}
