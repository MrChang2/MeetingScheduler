package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

abstract public class Database{
    private Connection database;

    public Database() throws Exception{
        database = connect();
        createTable();
    }

    public Connection connect() throws Exception{
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/company";
        String user = "cs356";
        String pass = user;

        Class.forName(driver);
        Connection connected = DriverManager.getConnection(url, user, pass);
        System.out.println("Connected to Database...");
        return connected;
    }

    public void createTable() throws Exception {
        PreparedStatement table = createFields();
        table.executeUpdate();
    }

    public Connection getDatabase(){return database;}

    protected abstract PreparedStatement createFields() throws Exception;

}
