package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RoomDB extends Database {
    private int numOfFields = 2;

    public RoomDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields() throws Exception {
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "room(room_id VARCHAR(10), occupied TINYINT(1), PRIMARY KEY(room_id))");
        return create;
    }

    public void insertRecord(String room_id, int occupied) throws Exception{
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO room(room_id VARCHAR(10), occupied TINYINT(1))" +
                "VALUES('"+room_id+"', '"+occupied+"')");
        insert.executeUpdate();
    }

    public void deleteRecord(String room_id) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("DELETE FROM room WHERE id=room_id");
        insert.executeUpdate();
    }

    public void updateRecord(String id, int occupied) throws Exception{
        String statement = String.format("UPDATE room SET '%s'='%s' WHERE room_id='%s'", "occupied", occupied, id);
        PreparedStatement update = super.getDatabase().prepareStatement(statement);
        update.executeUpdate();
    }

    public ResultSet getQuery() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM room");
        return retrieve.executeQuery();
    }

    public ArrayList<String> getAll() throws Exception{
        ResultSet retrieved = getQuery();
        ArrayList<String> retrievedInfo = new ArrayList<>();
        while(retrieved.next()){
            retrievedInfo.add(retrieved.getString("room_id"));
        }
        return retrievedInfo;
    }

    public ArrayList<String> getAvailable() throws Exception{
        ResultSet retrieved = getQuery();
        ArrayList<String> available = new ArrayList<>();
        while(retrieved.next()){
            if(retrieved.getString("occupied").equals("false")){
                available.add(retrieved.getString("room_id"));
            }
        }
        return available;
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM room WHERE room_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("room_id");
        array[1] = retrieved.getString("occupied");
        return array;
    }
}
