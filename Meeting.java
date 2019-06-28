import java.util.ArrayList;
public class Meeting {
    private String ownerID;
    private Room room;
    private ArrayList<Employee> attendees;

    public Meeting() {

    }

    public Meeting(String id, Room r) {
        ownerID = id;
        room = r;
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

    public void getAttendee() {

    }
}
