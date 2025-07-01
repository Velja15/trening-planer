package com.safetravel.treningplaner.test;
import com.safetravel.treningplaner.Models.DatabaseConnector;
import com.safetravel.treningplaner.Models.Korisnik;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectorTest {
    @Test
    void testInsertKorisnikSuccess(){
        DatabaseConnector db = new DatabaseConnector();
        Korisnik k = new Korisnik("testuser123","testpass123");

        boolean result = db.insertKorisnik(k);

        assertTrue(result,"Korisnik bi trebalo da bude uspesno dodat u bazu");

    }

    @Test
    void testInsertDuplicateKorisnik() {
        DatabaseConnector db = new DatabaseConnector();
        Korisnik k1 = new Korisnik("testuser456", "pass123");
        Korisnik k2 = new Korisnik("testuser456", "pass123"); // isti username kao k1

        boolean firstInsert = db.insertKorisnik(k1);
        boolean secondInsert = db.insertKorisnik(k2);

        assertTrue(firstInsert, "Prvi unos korisnika bi trebalo da uspe");
        assertFalse(secondInsert, "Unos duplikata korisnika ne bi trebalo da uspe");
    }

    @Test
    void testInsertKorisnikWithEmptyUsername() {
        DatabaseConnector db = new DatabaseConnector();
        Korisnik k = new Korisnik("", "somepass");

        boolean result = db.insertKorisnik(k);

        assertFalse(result, "Ne bi trebalo dozvoliti unos korisnika sa praznim korisničkim imenom");
    }
}
