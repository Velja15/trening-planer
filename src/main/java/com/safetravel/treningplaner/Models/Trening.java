package com.safetravel.treningplaner.Models;

public class Trening {
    private int id;
    private int dan;
    private String ime;
    private int tip_vezbe;
    private int zavrsen;

    public Trening(int dan, String ime, int tip_vezbe, int zavrsen) {
        this.dan = dan;
        this.ime = ime;
        this.tip_vezbe = tip_vezbe;
        this.zavrsen = zavrsen;
    }
    public Trening(String ime)
    {
        this.ime = ime;
    }



    public int getDan() {
        return dan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getTip_vezbe() {
        return tip_vezbe;
    }

    public void setTip_vezbe(int tip_vezbe) {
        this.tip_vezbe = tip_vezbe;
    }

    public int getZavrsen() {
        return zavrsen;
    }

    public void setZavrsen(int zavrsen) {
        this.zavrsen = zavrsen;
    }

    @Override
    public String toString() {
        return "Trening{" +
                "id=" + id +
                ", dan=" + dan +
                ", ime='" + ime + '\'' +
                ", tip_vezbe=" + tip_vezbe +
                ", zavrsen=" + zavrsen +
                '}';
    }
}
