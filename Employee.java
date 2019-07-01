import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDateTime;

import database.*;

public class Employee {
   private String employeeID;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String position;
    private WorkHours availability;
    private boolean isAdmin = false;
<<<<<<< HEAD
    private EmployeeDB employeeDB;
    private LoginDB loginDB;
    private ScheduleDB scheduleDB;
    private MeetingDB meetingDB;
    private RoomDB roomDB;
=======

    public Employee() {
>>>>>>> 7e96043f4ed65bf13ec4c5bf5696262c28206f71

    public Employee(){
    }
<<<<<<< HEAD
   public Employee(String id, String first, String last, String user, String pass, String pos, String start, String end) throws Exception{
=======
    public Employee(String user, String pass) {
        username = user;
        password = pass;
    }
    public Employee(String id, String first, String last, String user, String pass, String pos, WorkHours_Database wh) {
>>>>>>> 7e96043f4ed65bf13ec4c5bf5696262c28206f71
        employeeID = id;
        first_name = first;
        last_name = last;
        username = user;
        password = pass;
        position = pos;
<<<<<<< HEAD
        availableStart = start;
        availableEnd = end;
        saveToDB();
        meetingDB = new MeetingDB();
        roomDB = new RoomDB();
=======
        availability = new WorkHours(id);
        wh.getWorkHoursDatabase().add(availability);
    }
    
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
>>>>>>> 7e96043f4ed65bf13ec4c5bf5696262c28206f71
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
    
    //***************GUI BUTTON*************************
     public void changeFirst_name() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String firstName = sc.next();
        this.setFirst_name(firstName);
        System.out.println("First name successfully changed.");
    }

    public void setLast_name(String last_name) throws Exception {
        this.last_name = last_name;
        updateInfo("last_name", last_name);
    }

    public String getLast_name() {
        return last_name;
    }
    
    //***************GUI BUTTON*************************
    public void changeLast_name() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your last name: ");
        String lastName = sc.next();
        this.setLast_name(lastName);
        System.out.println("Last name successfully changed.");
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
    
<<<<<<< HEAD
     public void changePassword() throws Exception{
=======
    //***************GUI BUTTON*************************
     public void changePassword() {
>>>>>>> 7e96043f4ed65bf13ec4c5bf5696262c28206f71
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your new password: ");
        String password = sc.next();
        this.setPassword(password);
        System.out.println("Password successfully changed.");
    }

    public void setPosition(String position) throws Exception{
        this.position = position;
        updateInfo("position", position);
    }

    public String getPosition() {
        return position;
    }
    
    //***************GUI BUTTON*************************
    public void changePosition() {
        Scanner sc = new Scanner(System.in);
        System.out.println("State your position: ");
        String position = sc.next();
        this.setPosition(position);
        System.out.println("Position successfully changed.");
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
        employeeDB = new EmployeeDB();
        employeeDB.insertRecord(employeeID, first_name, last_name, position, isAdmin);
    }

    public void saveLogin() throws Exception{
        loginDB = new LoginDB();
        loginDB.insertRecord(employeeID, username, password);
    }

    public void saveSchedule() throws Exception{
        scheduleDB = new ScheduleDB();
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

    //***************GUI BUTTON*************************
    //Allows Employee to create meeting, adding it to the meeting database and selecting room from room database
    public void createMeeting() throws Exception{
       ArrayList<String> emptyRooms = roomDB.getAvailable();
       /*
        ~~~~~~~~~~~~~~~ NEED MENU LISTING ALL ROOMS HERE ~~~~~~~~~~~~~~~
        1. Display All Rooms
        2. Store user input
        */

       String selected_room = null;
       String meetingID = generateMeetingID();
       meetingDB.insertRecord(meetingID, selected_room, employeeID, meetingID);
       roomDB.updateRecord(selected_room, 1);

       /*
       ~~~~~~~~~~~~~~~ PROMPT: MENU CREATED ~~~~~~~~~~~~~~~
        */
    }

    //***************GUI BUTTON*************************
    //Allows employee to invite other employees to meeting
    public void inviteEmployee(Employee_Database employees, Meeting_Database meetings) throws Exception{
        HashMap<String, String> allEmployees = employeeDB.getAll();
        /*
        ~~~~~~~~~~~~~~~ NEED MENU LISTING ALL EMPLOYEES HERE ~~~~~~~~~~~~~~~
        1. Choose time slot (24hr format)
        2. Display All Employees
        3. Store user input
        */
        String selected_time = null;

        for(int i = 0; i < allEmployees.size(); i++){

        }


    }
    public void inviteEmployee(Meeting_Database meetings, String meetingN, Employee e) {
        for (int x=0;x<meetings.getMeetingDatabase().size();x++) {
            if (meetings.getMeetingDatabase().get(x).getMeetingName().equals(meetingN)) {
                if (meetings.getMeetingDatabase().get(x).getOwnerID().equals(this.getEmployeeID())) {
                    if (!meetings.getMeetingDatabase().get(x).getAttendees().contains(e)) {
                        meetings.getMeetingDatabase().get(x).invite(e);
                        System.out.println(e.getUsername() + " has been invited to " + meetingN + "!");
                    }
                    else {
                        System.out.println(e.getUsername() + " has already been invited.");
                    }
                }
                else {
                    System.out.println("You are not the owner of this room;");
                }
                break;
            }
            if (x==meetings.getMeetingDatabase().size()-1) {
                System.out.println("Meeting does not exist. Create a meeting if you want to invite someone.");
            }
        }
    }
    
    //***************GUI BUTTON*************************
    //Allows owners to uninvite an attendee
    public void uninviteEmployee(Employee_Database employees, Meeting_Database meetings, Notification_Database notifications) {
        Scanner sc = new Scanner(System.in);
        Employee uninvitedEmp = null;
        System.out.println("Name the meeting you wish to access: ");
        String meetingName = sc.next();
        System.out.println("What is the username of the Employee you would like to uninvite: ");
        String employeeUsername = sc.next();
        for (int x=0;x<employees.getEmployeeDatabase().size();x++) {
            if (employeeUsername.equals(employees.getEmployeeDatabase().get(x).getUsername())) {
                uninvitedEmp = employees.getEmployeeDatabase().get(x);
            }
        }
        if (uninvitedEmp!=null) {
            this.uninviteEmployee(meetings, notifications, meetingName, uninvitedEmp);
        }
        else {
            System.out.println("Username is not registered.");
        }
    }
    public void uninviteEmployee(Meeting_Database meetings, Notification_Database notifications, String meetingName, Employee emp) {
        for (int x=0;x<meetings.getMeetingDatabase().size();x++) {      //Iterate through Meeting Database
            if (meetings.getMeetingDatabase().get(x).getMeetingName().equals(meetingName)) {    //Once meeting is found
                if (meetings.getMeetingDatabase().get(x).getOwnerID().equals(this.getEmployeeID())) {   //if user owns the meeting
                    if (meetings.getMeetingDatabase().get(x).getAttendees().contains(emp)) {    //if employee has already been invited
                        meetings.getMeetingDatabase().get(x).getAttendees().remove(emp);        //remove the employee from the attendee list
                        String message = emp.getUsername() + " has been removed from " + meetings.getMeetingDatabase().get(x).getMeetingName() + ".";
                        Notification disinvitation;
                        //Notify all remaining attendees of the disinvitation
                        for (int y=0;y<meetings.getMeetingDatabase().get(x).getAttendees().size();y++) {
                            disinvitation = new Notification(meetings.getMeetingDatabase().get(x).getAttendees().get(y).getEmployeeID(), message);
                            notifications.getNotificationDatabase().add(disinvitation);
                        }
                    }
                    else {
                        System.out.println(emp.getUsername() + " has not been invited to this meeting.");
                    }
                }
                else {
                   System.out.println("You are not the owner of this meeting.");
                }
                break;
            }
        }
    }
    
    //***************GUI BUTTON*************************
    public void changeMeetingRoom(Room_Database rooms, Meeting_Database meetings, Notification_Database notifications) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What meeting do you want to modify: ");
        String meetingName = sc.next();
        System.out.println("What room do you want to move to(Enter room ID): ");
        String roomID = sc.next();
        for (int x=0;x<meetings.getMeetingDatabase().size();x++) {
            //if meeting is found
            if (meetings.getMeetingDatabase().get(x).getMeetingName().equals(meetingName)) {
                //if user owns the meeting
                if (meetings.getMeetingDatabase().get(x).getOwnerID().equals(this.getEmployeeID())) {
                    for (int y=0;y<rooms.getRooms().size();y++) {
                        //if room exists
                        if (rooms.getRooms().get(y).getRoomID().equals(roomID)) {
                            //set new room to meeting
                            meetings.getMeetingDatabase().get(x).setRoom(rooms.getRooms().get(y));
                            //notify all attendees
                            String message = meetings.getMeetingDatabase().get(x).getMeetingName() + " has been moved to room " + rooms.getRooms().get(y).getRoomID();
                            Notification roomMoved;
                            for (int z=0;z<meetings.getMeetingDatabase().get(x).getAttendees().size();z++) {
                                roomMoved = new Notification(meetings.getMeetingDatabase().get(x).getAttendees().get(z).getEmployeeID(), message);
                                notifications.getNotificationDatabase().add(roomMoved);
                            }
                            break;
                        }
                        if (y==rooms.getRooms().size()-1) {
                            System.out.println("Room does not exist.");
                        }
                    }
                }
                else {
                    System.out.println("You do not own this meeting.");
                }
                break;
            }
        }
    }

    //***************GUI BUTTON*************************
    public void cancelMeeting(Meeting_Database meetings, Notification_Database notifications) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the meeting you want to cancel:");
        String meetingName = sc.next();
        for (int x=0;x<meetings.getMeetingDatabase().size();x++) {
            //when meeting is found
            if (meetings.getMeetingDatabase().get(x).getMeetingName().equals(meetingName)) {
                //if user is owner
                if (meetings.getMeetingDatabase().get(x).getOwnerID().equals(this.getEmployeeID())) {
                    //notification message
                    String meetingCancelled = meetings.getMeetingDatabase().get(x).getMeetingName() + " has been cancelled.";
                    Notification cancellation;
                    //notify all attendees of cancellation
                    for (int y=0;y<meetings.getMeetingDatabase().get(x).getAttendees().size();y++) {
                        cancellation = new Notification(meetings.getMeetingDatabase().get(x).getAttendees().get(y).getEmployeeID(), meetingCancelled);
                        notifications.getNotificationDatabase().add(cancellation);
                    }
                    //remove the meeting
                    meetings.getMeetingDatabase().remove(meetings.getMeetingDatabase().get(x));
                }
                //else nothing happens
                else {
                    System.out.println("You are not the owner of this meeting.");
                }
                break;
            }
            if (x==meetings.getMeetingDatabase().size()-1) {
                System.out.println("Meeting does not exist.");
            }
        }
    }
    
     //***************GUI BUTTON*************************
    //Allows Employees to alter their work hours
    public void changeWorkingHours(WorkHours_Database wh) {
        Scanner sc = new Scanner(System.in);
        int input = 9;
        while (input!=6 && input!=7 && input!=8) {
            availability.printWorkHours();
            System.out.println("Enter the hour you wish to alter. Or enter 6, 7, or 8 to exit.");
            input = sc.nextInt();
            if (input<=12 && input>8) {
                availability.toggleAvailability(input-9);
            }
            else if (input<=5 && input>0){
                availability.toggleAvailability(input+3);
            }
        }
        wh.updateWorkHours(availability);
        System.out.println("Hours have been changed.");
    }

    //***************GUI BUTTON*************************
    //Allows Employees to check their schedules.
    public void checkWorkingHours() {
        availability.printWorkHours();
    }
}
