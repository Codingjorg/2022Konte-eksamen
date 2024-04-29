package com.example.konteeksamen2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Repository
public class PAKKEREPOSITORY {

    @Autowired
    private JdbcTemplate db;


    private boolean sjekkPassord(String passord, String hashPassord){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if(bCrypt.matches(hashPassord,passord)){
            return true;
        }
        return false;
    }

    public boolean sjekkEpostOgPassord (Bruker bruker) {
        String sql = "SELECT * FROM BRUKER WHERE epost=?";
        try{
            Bruker dbBruker = db.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(Bruker.class),new Object[]{bruker.getEpost()});
            return sjekkPassord(dbBruker.getPassord(),bruker.getPassord());
        }
        catch(Exception e) {
            logger.error("Feil i sjekkNavnOgPassord : " + e);
            return false;
        }
    }

    public String krypterPassord(String passord){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(14);
        String hashedPassword = bcrypt.encode(passord);
        return hashedPassword;
    }

    public
    int brukerIDincreaser = 3;
    public boolean lagreEnBruker(Bruker bruker){



    String sql = "INSERT INTO  BRUKER(brukerid, epost, fornavn,etternavn, passord)VALUES(?,?,?,?,?)";
    String hash =krypterPassord(bruker.getPassord());
    try {
        db.update(sql, brukerIDincreaser, bruker.getEpost(), bruker.getFornavn(), bruker.getEtternavn(), hash);
        brukerIDincreaser++;
        return true;
    }catch(Exception e){
            logger.error("Feil i lagrePakke : " + e);
            return false;
        }
    }

    public int pakkeIDincreaser = 3;

    //PakkeID lager seg selv så vi trenger ikke putte det inn

    private static final Logger logger = LoggerFactory.getLogger(PAKKEREPOSITORY.class);
// Skal være 16 feil ift tabellnavn og tabellruter i denne classen.


    public boolean lagrePakke(Pakke pakke) {
        String sql = "INSERT INTO PAKKE(pakkeid,lagerid,eierid,vekt,volum) VALUES (?,?,?,?,?)";
        try {
            db.update(sql, pakkeIDincreaser, pakke.getLagerid(), pakke.getEierid(), pakke.getVekt(), pakke.getVolum());
            pakkeIDincreaser++;
            return true;
        } catch (Exception e) {
            logger.error("Feil i lagrePakke : " + e);
            return false;
        }
    }



//    Gamle metoden før vi hadde serverside inputvalidering
//    public void lagreEnPakke(Pakke pakke){
//        String sql = "INSERT INTO PAKKE(pakkeid,lagerid,eierid,vekt,volum)VALUES(?,?,?,?,?)";
//        db.update(sql,pakkeIDincreaser,pakke.getLagerid(),pakke.getEierid(),pakke.getVekt(),pakke.getVolum());
//        pakkeIDincreaser++;
//    }

    public int tellPakkerPerLager(int lagerid){
        String sql = "Select COUNT(PAKKE.lagerid) from PAKKE where lagerid=?";
        int antall = db.queryForObject(sql, Integer.class,lagerid);
        return antall;
    }

    public double summerVektAvPakkerPerLager(int lagerid){
        System.out.println("Nå henter vi ut vekta");
        String sql = "Select SUM(vekt) from PAKKE where lagerid=?";
        double totalvekt = db.queryForObject(sql, Double.class,lagerid);
        return totalvekt;
    }

    public double summerVolumAvPakkerPerLager(int lagerid){
        System.out.println("Nå henter vi ut volumet");
        String sql = "Select SUM(volum) from PAKKE where lagerid=?";
        double  totalvolum = db.queryForObject(sql, Double.class,lagerid);
        return totalvolum;
    }

    public List<Pakke> hentAllePakker() {
        String sql = "SELECT pakke.* FROM PAKKE";
        List<Pakke> allePakker = db.query(sql,new BeanPropertyRowMapper(Pakke.class));
        return allePakker;
    }

    public List<Lager> hentAlleLager() {
        String sql = "SELECT lager.* FROM LAGER";
        List<Lager> allePakker = db.query(sql,new BeanPropertyRowMapper(Lager.class));
        return allePakker;
    }

    public String hentEtLagernavn(int lagerid) {
        String sql = "SELECT distinct lager.lagernavn FROM LAGER where lagerid =? limit 1";
        String returnString = db.queryForObject(sql, String.class, lagerid);
        return returnString;
    }
    public String hentEnEmail(int eierid) {
        String sql = "SELECT distinct bruker.epost FROM BRUKER where brukerid =? limit 1";
        String returnString = db.queryForObject(sql, String.class, eierid);
        return returnString;
    }

    public String hentHoyesteBrukerID() {
        String sql = "SELECT max(brukerid) FROM BRUKER limit 1";
        String returnString = db.queryForObject(sql, String.class);
        return returnString;
    }
}
