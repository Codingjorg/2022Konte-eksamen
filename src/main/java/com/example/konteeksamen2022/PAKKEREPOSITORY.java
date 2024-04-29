package com.example.konteeksamen2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PAKKEREPOSITORY {

    @Autowired
    private JdbcTemplate db;

    public void lagreEnPakke(Pakke pakke){
        String sql = "INSERT INTO PAKKE(PAKKE_ID,LAGER_ID,EIER_ID,VEKT,VOLUM)VALUES(?,?,?,?,?)";
        db.update(sql,pakke.getPAKKE_ID(),pakke.getLAGER_ID(),pakke.getEIER_ID(),pakke.getVEKT(),pakke.getVOLUM());
    }

    public List<Pakke> hentAllePakker() {
        String sql = "SELECT * FROM PAKKE";
        List<Pakke> allePakker = db.query(sql,new BeanPropertyRowMapper(Pakke.class));
        return allePakker;
    }

}
