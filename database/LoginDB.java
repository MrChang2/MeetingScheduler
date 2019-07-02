package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginDB extends Database {
    private static LoginDB loginDB;
    private int numOfFields = 3;

    private LoginDB() throws Exception{
        super();
    }

    public static LoginDB getInstance() throws Exception{
        if(loginDB == null){return new LoginDB();}
        return loginDB;
    }

    protected PreparedStatement createFields() throws Exception {
        PreparedStatement create = super.getDatabase().prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "login(employee_id VARCHAR(10), username VARCHAR(50), pass VARCHAR(50), PRIMARY KEY(username))");
        return create;
    }

    public void insertRecord(String id, String user, String pass) throws Exception{
        PreparedStatement insert = super.getDatabase().prepareStatement("INSERT INTO login(employee_id VARCHAR(10), username VARCHAR(50), pass VARCHAR(50)) " +
                "VALUES('"+id+"', '"+user+"', '"+pass+"')");
        insert.executeUpdate();
    }

    public void deleteRecord(String id) throws Exception {
        PreparedStatement insert = super.getDatabase().prepareStatement("DELETE FROM login WHERE employee_id=id");
        insert.executeUpdate();
    }

    public void updateRecord(String field, String updateThis, String id) throws Exception{
        String statement = String.format("UPDATE login SET '%s'='%s' WHERE employee_id='%s'", field, updateThis, id);
        PreparedStatement update = super.getDatabase().prepareStatement(statement);
        update.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM login");
        return super.getAll(retrieve);
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM login WHERE username=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("id");
        array[1] = retrieved.getString("user");
        array[2] = retrieved.getString("pass");
        if(super.isEmpty(array)){return null;}
        return array;
    }
}
