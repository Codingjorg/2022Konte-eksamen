package com.example.konteeksamen2022;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BrukerController {
    @Autowired
    PAKKEREPOSITORY rep;

   @Autowired
   private HttpSession session;

    @PostMapping("/lagBruker")
    public void lagBrukerCon(Bruker bruker){
        System.out.println(bruker.getFornavn() + "\n"+
                bruker.getEtternavn() + "\n" +
                bruker.getEpost() + "\n" +
                bruker.getPassord());
        rep.lagreEnBruker(bruker);
    }


    @GetMapping("/login")
    public boolean logInnCon(Bruker bruker){
        System.out.println(bruker.getEpost() + "\n" + bruker.getPassord());

        if(rep.sjekkEpostOgPassord(bruker)){
            session.setAttribute("Innlogget",bruker);
            return true;
        }
        return false;
    }


}
