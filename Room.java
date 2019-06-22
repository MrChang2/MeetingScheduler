public class Room {
    private String roomID;
    private boolean occupied;

    public Room() {

    }

    public Room(String id) {
        roomID = id;
        occupied = false;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomID() {
        return roomID;
    }
}
