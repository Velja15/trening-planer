package com.safetravel.treningplaner.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Korisnik {
    private int id;
    private final StringProperty username;
    private final StringProperty password;

    public Korisnik( String username, String password) {
        this.id = id;
        this.username = new SimpleStringProperty(this,"username",username);
        this.password = new SimpleStringProperty(this,"password",password);
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
