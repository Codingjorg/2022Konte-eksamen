package com.example.konteeksamen2022;

public class Lager {

    int lagerid;
    String lagernavn;
    String adresse;

    public Lager(int lagerid, String lagernavn, String adresse) {
        this.lagerid = lagerid;
        this.lagernavn = lagernavn;
        this.adresse = adresse;
    }

    public Lager(){};

    public int getLagerid() {
        return lagerid;
    }

    public void setLagerid(int lagerid) {
        this.lagerid = lagerid;
    }

    public String getLagernavn() {
        return lagernavn;
    }

    public void setLagernavn(String lagernavn) {
        this.lagernavn = lagernavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}