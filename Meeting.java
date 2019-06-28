import java.util.ArrayList;
public class Meeting {
    private String meetingName;
    private String ownerID;
    private Room room;
    private ArrayList<Employee> attendees;

    public Meeting() {

    }

    public Meeting(String n, String id, Room r) {
        meetingName = n;
        ownerID = id;
        room = r;
        attendees = new ArrayList<Employee>();
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void invite(Employee e) {
        attendees.add(e);
    }

    public ArrayList<Employee> getAttendees() {
        return attendees;
    }
}

