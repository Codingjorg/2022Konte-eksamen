package com.example.konteeksamen2022;

public class Pakke {

    int pakkeid;
    int lagerid;
    int eierid;
    double vekt;
    double volum;

    public Pakke(int pakkeid, int lagerid, int eierid, double vekt, double volum) {
        this.pakkeid = pakkeid;
        this.lagerid = lagerid;
        this.eierid = eierid;
        this.vekt = vekt;
        this.volum = volum;
    }

    public Pakke() {
    }

    public int getPakkeid() {
        return pakkeid;
    }

    public void setPakkeid(int pakkeid) {
        this.pakkeid = pakkeid;
    }

    public int getLagerid() {
        return lagerid;
    }

    public void setLagerid(int lagerid) {
        this.lagerid = lagerid;
    }

    public int getEierid() {
        return eierid;
    }

    public void setEierid(int eierid) {
        this.eierid = eierid;
    }

    public double getVekt() {
        return vekt;
    }

    public void setVekt(double vekt) {
        this.vekt = vekt;
    }

    public double getVolum() {
        return volum;
    }

    public void setVolum(double volum) {
        this.volum = volum;
    }
}
