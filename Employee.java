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

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
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

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
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
}
