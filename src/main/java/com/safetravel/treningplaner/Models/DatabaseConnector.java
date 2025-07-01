package com.safetravel.treningplaner.Models;

import java.sql.*;

public class DatabaseConnector {

    public static Connection conn;
    String url = "jdbc:mysql://localhost:3306/treningplanerrdb";
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

    public static boolean insertTrening(Trening trening)
    {
        try
        {
            String ime = trening.getIme();
            int dan = trening.getDan();
            int tip_vezbe = trening.getTip_vezbe();
            int zavrsen = trening.getZavrsen();

            PreparedStatement preparedStatement;

            preparedStatement = conn.prepareStatement("INSERT INTO TRENING (ime,tip_vezbe,zavrsen,dan) VALUES(?,?,?,?)");
            preparedStatement.setString(1,ime);
            preparedStatement.setInt(2,tip_vezbe);
            preparedStatement.setInt(3,zavrsen);
            preparedStatement.setInt(4,dan);

            int rowsInserted = preparedStatement.executeUpdate();

            if(rowsInserted > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertKorisnik(Korisnik korisnik) {
        try{
            String Username = korisnik.getUsername();
            String Password = korisnik.getPassword();

            PreparedStatement preparedStatement;

            preparedStatement = conn.prepareStatement("INSERT INTO KORISNIK (password,username) VALUES (?,?)");
            preparedStatement.setString(1,Password);
            preparedStatement.setString(2,Username);

            int rowsInserted = preparedStatement.executeUpdate();

            if(rowsInserted > 0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static ResultSet getVezbe(String dan){
        Statement statement;
        ResultSet resultSet = null;
        int dan_U_Int = 0;
        if(dan.equals("Ponedeljak")){
            dan_U_Int = 1;
        }else if(dan.equals("Utorak")){
            dan_U_Int = 2;
        }else if(dan.equals("Sreda")){
            dan_U_Int = 3;
        }else if(dan.equals("Cetvrtak")){
            dan_U_Int = 4;
        }else if(dan.equals("Petak")){
            dan_U_Int = 5;
        }else if(dan.equals("Subota")){
            dan_U_Int = 6;
        }else if(dan.equals("Nedelja")){
            dan_U_Int = 7;
        }else{
            dan_U_Int = 1;
        }

        try{
            PreparedStatement preparedStatement;

            preparedStatement = conn.prepareStatement("SELECT * FROM TRENING WHERE dan = ?");
            preparedStatement.setInt(1,dan_U_Int);

            resultSet = preparedStatement.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static boolean obrisiVezbu(String naziv, String dan)
    {
        int dan_U_Int = switch (dan) {
            case "Ponedeljak" -> 1;
            case "Utorak" -> 2;
            case "Sreda" -> 3;
            case "Cetvrtak" -> 4;
            case "Petak" -> 5;
            case "Subota" -> 6;
            case "Nedelja" -> 7;
            default -> 1;
        };

        try{
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM TRENING WHERE ime = ? AND dan = ?");
            preparedStatement.setString(1,naziv);
            preparedStatement.setInt(2,dan_U_Int);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
