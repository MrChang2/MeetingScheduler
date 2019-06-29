package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDB extends Database {
    private int numOfFields = 5;

    public EmployeeDB() throws Exception{
        super();
    }

    protected PreparedStatement createFields(Connection database) throws Exception{
        PreparedStatement create = database.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "employee(employee_id VARCHAR(10), first_name VARCHAR(50), last_name VARCHAR(50)," +
                "position VARCHAR(50), admin TINYBIT(1) PRIMARY KEY(employee_id))");
        return create;
    }

    public void insertFields(Connection database, String id, String first_name, String last_name, String position, int admin) throws Exception {
        PreparedStatement insert = database.prepareStatement("INSERT INTO employee(employee_id VARCHAR(10), first_name VARCHAR(50), last_name VARCHAR(50)," +
                "position VARCHAR(50), admin TINYBIT(1)) VALUES('"+id+"', '"+first_name+"', '"+last_name+"', '"+position+"', '"+admin+"')");
        insert.executeUpdate();
    }

    public void deleteFields(Connection database, String id) throws Exception {
        PreparedStatement insert = database.prepareStatement("DELETE FROM employee WHERE employee_id=id");
        insert.executeUpdate();
    }

    public ArrayList<ResultSet> getAll() throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM employee");
        ResultSet retrieved = retrieve.executeQuery();

        ArrayList<ResultSet> retrievedInfo = new ArrayList<ResultSet>();
        while(retrieved.next()){
            retrievedInfo.add(retrieved);
        }
        return retrievedInfo;
    }

    public String[] getThis(String id) throws Exception{
        PreparedStatement retrieve = super.getDatabase().prepareStatement("SELECT * FROM employee WHERE employee_id=id");
        ResultSet retrieved = retrieve.executeQuery();
        String[] array = new String[numOfFields];

        retrieved.next();
        array[0] = retrieved.getString("employee_id");
        array[1] = retrieved.getString("first_name");
        array[2] = retrieved.getString("last_name");
        array[3] = retrieved.getString("position");
        array[4] = retrieved.getString("admin");
        return array;
    }

}
