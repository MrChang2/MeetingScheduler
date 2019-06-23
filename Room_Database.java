import java.util.ArrayList;
public class Room_Database {
    private ArrayList<Room> rooms;

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public String viewRoom(Room r) {
        Room selected = rooms.get(rooms.indexOf(r));
        String room = "Room ID: " + selected.getRoomID();
        if (selected.isOccupied()) {
            room = room + "\n" + "Occupied: Yes";
        }
        else {
            room = room + "\n" + "Occupied: No";
        }
        return room;
    }
}
