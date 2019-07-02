import database.EmployeeDB;
import database.RoomDB;
import database.MeetingDB;
import database.LoginDB;
import database.ScheduleDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Employee {
    private String employeeID;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String position;
    private String availableStart;
    private String availableEnd;
    private boolean isAdmin = false;
    private static EmployeeDB employeeDB;
    private static LoginDB loginDB;
    private static ScheduleDB scheduleDB;
    private static MeetingDB meetingDB;
    private static RoomDB roomDB;

   public Employee(String id, String first, String last, String user, String pass, String pos, String start, String end) throws Exception{
        employeeID = id;
        first_name = first;
        last_name = last;
        username = user;
        password = pass;
        position = pos;
        availableStart = start;
        availableEnd = end;
        saveToDB();
        meetingDB = MeetingDB.getInstance();
        roomDB = RoomDB.getInstance();
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setFirst_name(String first_name) throws Exception {
        this.first_name = first_name;
        updateInfo("first_name", first_name);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) throws Exception {
        this.last_name = last_name;
        updateInfo("last_name", last_name);
    }

    public String getLast_name() {
        return last_name;
    }

    public void setUsername(String username) throws Exception {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) throws Exception{
        this.password = password;
        updateLogin("pass", password);
    }

    public String getPassword() {
        return password;
    }
    
    public void changePassword(String newPassword, String employeeID) throws Exception{
        loginDB.updateRecord("pass", newPassword, employeeID);
    }

    public void setPosition(String position) throws Exception{
        this.position = position;
        updateInfo("position", position);
    }

    public String getPosition() {
        return position;
    }

    public void setAvailableStart(String availableStart) throws Exception{
        this.availableStart = availableStart;
        updateSchedule("start_hour", availableStart);
    }

    public String getAvailableStart() {
        return availableStart;
    }

    public void setAvailableEnd(String availableEnd) throws Exception {
        this.availableEnd = availableEnd;
        updateSchedule("end_hour", availableEnd);
    }

    public String getAvailableEnd() {
        return availableEnd;
    }

    public void setAdmin(boolean admin) throws Exception {
        isAdmin = admin;
        updateAdmin();
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void saveToDB() throws Exception{
        saveInfo();
        saveLogin();
    }

    public void saveInfo() throws Exception{
        employeeDB = EmployeeDB.getInstance();
        employeeDB.insertRecord(employeeID, first_name, last_name, position, isAdmin);
    }

    public void saveLogin() throws Exception{
        loginDB = LoginDB.getInstance();
        loginDB.insertRecord(employeeID, username, password);
    }

    public void saveSchedule() throws Exception{
        scheduleDB = ScheduleDB.getInstance();
        scheduleDB.insertRecord(employeeID, availableStart, availableEnd);
    }

    public void updateInfo(String field, String updateThis) throws Exception{
        employeeDB.updateRecord(field, updateThis, employeeID);
    }

    public void updateAdmin() throws Exception{
        employeeDB.updateRecord(isAdmin, employeeID);
    }

    public void updateLogin(String field, String updateThis) throws Exception{
        loginDB.updateRecord(field, updateThis, employeeID);
    }

    public void updateSchedule(String field, String updateThis) throws Exception{
        scheduleDB.updateRecord(field, updateThis, employeeID);
    }

    public String generateMeetingID(){
        return LocalDateTime.now().toString();
    }

    public String getEndTime(String time, int duration){
        String doubleDigits;
        String[] times = time.split(":");
        int end_hr = Integer.parseInt(times[0]);
        int end_min = Integer.parseInt(times[1]);

        int result_min = end_min + duration;
        if(result_min >= 60){
            end_hr += result_min/60;
            end_min = result_min % 60;
        }
        if(end_min < 10){
            doubleDigits = "0" + Integer.toString(end_min);
        }
        else{
            doubleDigits = Integer.toString(end_min);
        }
        return end_hr + ":" + doubleDigits;
    }

    //Allows Employee to create meeting, adding it to the meeting database and selecting room from room database
    public String[] createMeeting() throws Exception{
       ArrayList<String> emptyRooms = roomDB.getAvailable();
       /*
        ~~~~~~~~~~~~~~~ NEED MENU LISTING ALL ROOMS HERE ~~~~~~~~~~~~~~~
        1. Display All Rooms
        2. Choose time slot (24hr format) and duration (min)
        3. Store user input
        */
       String selected_room = null;
       String meetingID = generateMeetingID();
       String start_time = null;
       int duration = 0;
       String end_time = getEndTime(start_time, duration);

       meetingDB.insertRecord(meetingID, selected_room, start_time, end_time, employeeID, meetingID);
       roomDB.updateRecord(selected_room, 1);

       return new String[] {meetingID, selected_room, start_time, end_time};
       /*
       ~~~~~~~~~~~~~~~ PROMPT: MENU CREATED ~~~~~~~~~~~~~~~
        */
    }

    //Allows employee to invite other employees to meeting
    public void inviteEmployee(String[] meetingInfo) throws Exception{
        ArrayList<String> validEmployee = employeeDB.getValidEmployees(meetingInfo[2], meetingInfo[3]);
        int hour = 0;
        int minutes = 0;
        /*
        ~~~~~~~~~~~~~~~ NEED MENU LISTING ALL EMPLOYEES HERE ~~~~~~~~~~~~~~~
        1. Display All Employees
        2. Store user input
        */

        String selected_employee = null;
        meetingDB.insertRecord(meetingInfo[0], meetingInfo[1], meetingInfo[2], meetingInfo[3], selected_employee, null);
    }

    //Allows owners to uninvite an attendee
    public void uninviteEmployee(String employee) throws Exception{
        meetingDB.deleteRecord(employee);
    }

    public void changeMeetingRoom(String meetingID, String newRoom) throws Exception{
        meetingDB.updateRecord("room_id", newRoom, "meeting_id", meetingID);
    }

    public void cancelMeeting(String meetingID) throws Exception{
        meetingDB.deleteRecord(meetingID);
    }
}
