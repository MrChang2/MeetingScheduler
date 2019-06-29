package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginDB extends Database {
    private int numOfFields = 3;

    public LoginDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields(Connection database) throws Exception {
        PreparedStatement create = database.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "login(employee_id VARCHAR(10), user VARCHAR(50), pass VARCHAR(50), PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertFields(Connection database, String user, String pass) throws Exception{
        PreparedStatement insert = database.prepareStatement("INSERT INTO login(employee_id VARCHAR(10), user VARCHAR(50), pass VARCHAR(50)) " +
                "VALUES('"+user+"', '"+pass+"')");
        insert.executeUpdate();
    }

    public void deleteFields(Connection database, String id) throws Exception {
        PreparedStatement insert = database.prepareStatement("DELETE FROM login WHERE employee_id=id");
        insert.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM login");
        ResultSet retrieved = retrieve.executeQuery();

        ArrayList<ResultSet> retrievedInfo = new ArrayList<ResultSet>();
        while(retrieved.next()){
            retrievedInfo.add(retrieved);
        }
        return retrievedInfo;
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM login WHERE employee_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("id");
        array[1] = retrieved.getString("user");
        array[2] = retrieved.getString("pass");
        return array;
    }
}
