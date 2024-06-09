package com.example.konteeksamen2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.List;

@Repository
public class PAKKEREPOSITORY {

    private static final Logger logger = LoggerFactory.getLogger(PAKKEREPOSITORY.class);

    @Autowired
    private JdbcTemplate db;


    private boolean sjekkPassord(String passord, String hashPassord){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if(bCrypt.matches(passord,hashPassord)){
            logger.info(bCrypt.matches(passord,hashPassord) + " var resultatet av sjekkPassord");
            return true;
        }
        return false;
    }

    public boolean sjekkEpostOgPassord (Bruker bruker) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM BRUKER WHERE epost=?";
        try{
//            Bruker dbBruker = db.queryForObject(sql,
//                    BeanPropertyRowMapper.newInstance(Bruker.class),new Object[]{bruker.getEpost()});
            Bruker dbBruker = db.queryForObject(sql, new BeanPropertyRowMapper<>(Bruker.class), bruker.getEpost());
            logger.info("sjekkEpostOgPassord ble kjørt");
            return sjekkPassord(bruker.getPassord(), dbBruker.getPassord());

        }
        catch(Exception e) {
            logger.error("Feil i sjekkEpostOgPassord : " + e);
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
            logger.error("Feil i lagreEnBruker : " + e);
            return false;
        }
    }

    public int pakkeIDincreaser = 3;

    //PakkeID lager seg selv så vi trenger ikke putte det inn

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

    public String tellPakkerPerLager(int lagerid){
        String sql = "Select COUNT(PAKKE.lagerid) from PAKKE where lagerid=?";
        try{
        String antall = db.queryForObject(sql, String.class,lagerid);
        return antall;}
        catch(Exception e){
            logger.error("TellPakkerPerLager funksjonen fikk en error");
            return "ERROR";
        }
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
        try{
        List<Pakke> allePakker = db.query(sql,new BeanPropertyRowMapper(Pakke.class));
        return allePakker;}catch(Exception e){
            return null;
        }
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
