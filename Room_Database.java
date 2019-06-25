import java.util.ArrayList;
public class Room_Database {
    private ArrayList<Room> rooms;

    public Room_Database() {
        rooms = new ArrayList<Room>();
    }
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

    public void viewAllRooms() {
        for (int x=0;x<rooms.size();x++) {
            System.out.println(this.viewRoom(rooms.get(x)));
        }
    }
}
