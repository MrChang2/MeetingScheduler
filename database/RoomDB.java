package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RoomDB extends Database {
    private int numOfFields = 2;

    public RoomDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields(Connection database) throws Exception {
        PreparedStatement create = database.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "room(room_id VARCHAR(10), occupied TINYINT(1), PRIMARY KEY(room_id))");
        return create;
    }

    public void insertFields(Connection database, String room_id, int occupied) throws Exception{
        PreparedStatement insert = database.prepareStatement("INSERT INTO room(room_id VARCHAR(10), occupied TINYINT(1))" +
                "VALUES('"+room_id+"', '"+occupied+"')");
        insert.executeUpdate();
    }

    public void deleteFields(Connection database, String room_id) throws Exception {
        PreparedStatement insert = database.prepareStatement("DELETE FROM room WHERE id=room_id");
        insert.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM room");
        ResultSet retrieved = retrieve.executeQuery();

        ArrayList<ResultSet> retrievedInfo = new ArrayList<ResultSet>();
        while(retrieved.next()){
            retrievedInfo.add(retrieved);
        }
        return retrievedInfo;
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
