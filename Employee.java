import java.util.Scanner;
import database.EmployeeDB;

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
    private EmployeeDB database;

    public Employee() {

    }
   public Employee(String id, String first, String last, String user, String pass, String pos, String start, String end) {
        employeeID = id;
        first_name = first;
        last_name = last;
        username = user;
        password = pass;
        position = pos;
        availableStart = start;
        availableEnd = end;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    //***************GUI BUTTON*************************
     public void changePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your new password: ");
        String password = sc.next();
        this.setPassword(password);
        System.out.println("Password successfully changed.");
    }

    public void setPosition(String position) {
        this.position = position;
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

    public void setAvailableStart(String availableStart) {
        this.availableStart = availableStart;
    }

    public String getAvailableStart() {
        return availableStart;
    }

    public void setAvailableEnd(String availableEnd) {
        this.availableEnd = availableEnd;
    }

    public String getAvailableEnd() {
        return availableEnd;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void connectDB() throws Exception{
        database = new EmployeeDB();
    }

    //***************GUI BUTTON*************************
    //Allows Employee to create meeting, adding it to the meeting database and selecting room from room database
    /*
    public void createMeeting(Meeting_Database mdatabase, Room_Database rdatabase) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to name this meeting: ");
        String meetingN = sc.next();
        System.out.println("Enter ID of room: ");
        String roomID = sc.next();
        for (int x=0;x<rdatabase.getRooms().size();x++) {
            if (roomID.equals(rdatabase.getRooms().get(x).getRoomID())) {
                if (!rdatabase.getRooms().get(x).isOccupied()) {
                    Meeting newMeeting = new Meeting(meetingN, this.getEmployeeID(), rdatabase.getRooms().get(x));
                    mdatabase.getMeetingDatabase().add(newMeeting);
                    rdatabase.getRooms().get(x).setOccupied(true);
                }
                else {
                    System.out.println("Room is taken.");
                }
                break;
            }
            if (x==rdatabase.getRooms().size()-1) {
                System.out.println("Room does not exist.");
            }
        }
    }
    */

    //***************GUI BUTTON*************************
    //Allows employee to invite other employees to meeting
    public void inviteEmployee(Employee_Database employees, Meeting_Database meetings) {
        Scanner sc = new Scanner(System.in);
        Employee invitedE = null;
        System.out.println("What is the name of the meeting: ");
        String meetingN = sc.next();
        System.out.println("What is the username of the Employee you would like to invite: ");
        String employeeUsername = sc.next();
        for (int x=0;x<employees.getEmployeeDatabase().size();x++) {
            if (employeeUsername.equals(employees.getEmployeeDatabase().get(x).getUsername())) {
                invitedE = employees.getEmployeeDatabase().get(x);
            }
        }
        if (invitedE!=null) {
            this.inviteEmployee(meetings, meetingN, invitedE);
        }
        else {
            System.out.println("Username is not registered.");
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
}
