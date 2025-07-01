package com.safetravel.treningplaner.Models;

import com.safetravel.treningplaner.Controllers.LoginController;
import com.safetravel.treningplaner.Views.ViewFactory;

import java.sql.ResultSet;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseConnector databaseConnector;

    private Korisnik korisnik;
    private boolean korisnikUspesnoLogin;

    private Model() {
        this.databaseConnector = new DatabaseConnector();
        this.viewFactory = new ViewFactory();
        this.korisnikUspesnoLogin = false;
        this.korisnik = new Korisnik("","");
    }

    LoginController loginController = new LoginController();

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}

    public DatabaseConnector getDatabaseConnector(){
        return databaseConnector;
    }

    public boolean getKorisnikUspesnoLogin() {return this.korisnikUspesnoLogin;}

    public void setKorisnikUspesnoLogin(boolean uspesnoLogin) {this.korisnikUspesnoLogin = uspesnoLogin;}

    public Korisnik getKorisnik() {return this.korisnik;}

    public void proveriKorisnikUnos(String username, String password) {
        Korisnik korisnik;
        ResultSet resultSet = databaseConnector.getKorisnik(username,password);

        try{
            while(resultSet.next()){
                this.korisnik.usernameProperty().set(resultSet.getString("username"));
                this.korisnik.passwordProperty().set(resultSet.getString("password"));
                this.korisnikUspesnoLogin = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
