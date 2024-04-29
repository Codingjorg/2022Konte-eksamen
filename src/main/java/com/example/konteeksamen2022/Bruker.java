package com.example.konteeksamen2022;

public class Bruker {

    private int brukerid;
    private String epost;
    private String fornavn;
    private String etternavn;
    private String passord;

    public Bruker() {
    }

    public Bruker(int brukerid, String epost, String fornavn, String etternavn, String passord) {
        this.brukerid = brukerid;
        this.epost = epost;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.passord = passord;
    }

    public int getBrukerid() {
        return brukerid;
    }

    public void setBrukerid(int brukerid) {
        this.brukerid = brukerid;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
