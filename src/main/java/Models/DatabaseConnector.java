package Models;

import java.sql.*;

public class DatabaseConnector {

    public Connection conn;
    String url = "jdbc:mysql://localhost:3306/treningplanerdb";
    String user = "root";
    String password = "";

    public DatabaseConnector() {
        try {
            System.out.println("Connecting to database...");
            this.conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet getKorisnik(String Username, String Password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM KORISNIK WHERE username='" + Username + "' AND password='" + Password + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }


}
