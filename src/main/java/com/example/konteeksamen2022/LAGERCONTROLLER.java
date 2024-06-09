package com.example.konteeksamen2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpSession;

import java.util.List;
//import javax.servlet.http.HttpServletResponse;
// test ut importen over og den under
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
public class LAGERCONTROLLER {
    @Autowired
    PAKKEREPOSITORY rep;
    private Logger logger = LoggerFactory.getLogger(LAGERCONTROLLER.class);

    @Autowired
    private HttpSession session;

    @GetMapping("/hentAlleLagere")
    public List<Lager> hentAlleLagereCon(){
    return rep.hentAlleLager();
    }

    @GetMapping("/hentAllePakker")
    public List<Pakke> hentAllePakkerCon(HttpServletResponse response) throws IOException {
        System.out.println("Alle pakker ble hentet");
        if(session.getAttribute("Innlogget")!=null){   return rep.hentAllePakker();}else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    private boolean validerPakke(Pakke pakke) {
        String regexEierID = "[0-9]{1,50}";
        String regexLagerID = "[0-9]{1,50}";
        String EierIDString = String.valueOf(pakke.eierid);
        String LagerIDString = String.valueOf(pakke.lagerid);
        boolean regexEierIDOK = EierIDString.matches(regexEierID);
        boolean regexLagerIDOK = LagerIDString.matches(regexLagerID);
        if (regexEierIDOK && regexLagerIDOK) {
            System.out.println("Begge Verdiene var gyldige");
            return true;
        }
        logger.error("Valideringsfeil");
        System.out.println("En eller to av verdiene var ugyldig.");
        return false;
    }


    @PostMapping("/lagreEnPakke")
    public void lagreEnPakkeCon(Pakke pakke, HttpServletResponse response) throws IOException {
        System.out.println("En pakke ble lagret");
        if(validerPakke(pakke) == false) {
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Feil i Validering - prøv igjen senere")
            ;
        } else {
            if (!rep.lagrePakke(pakke)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i Validering - prøv igjen senere");
            }
        }
    }


//    @PostMapping("/hentEttLagernavn")
//    public void hentAllePakkerCon(int lagerid){
//        System.out.println("Lagernavn lagret, " + "iden var" + lagerid);
////        return rep.hentEtLagernavn(lagerid);
//    }

    @GetMapping ("/hentEttLagernavn")
    public String hentAlleLagernavnForPakkeTabellCon(int lagerid) {
        System.out.println("Lagernavn hentet, iden var " + lagerid);
        // Your logic to retrieve and return the lagernavn
        System.out.println(rep.hentEtLagernavn(lagerid));
        return rep.hentEtLagernavn(lagerid);
    }

    @GetMapping ("/hentLagerAntall")
    public String hentAllePakkeAntallforLagerTabellCon(int lagerid) {
        // Your logic to retrieve and return the lagernavn
        System.out.println(rep.tellPakkerPerLager(lagerid));
        return rep.tellPakkerPerLager(lagerid);
    }

    @GetMapping("/hentSumVektforLager")
    public double hentSumVektforLagerCon(int lagerid){
        System.out.println(rep.summerVektAvPakkerPerLager(lagerid));
        return rep.summerVektAvPakkerPerLager(lagerid);
    }

    @GetMapping("/hentSumVolumforLager")
    public double hentSumVolumforLagerCon(int lagerid){
        System.out.println(rep.summerVolumAvPakkerPerLager(lagerid));
        return rep.summerVolumAvPakkerPerLager(lagerid);
    }

    @GetMapping("/hentEnEpost")
    public String hentEnEpostCon(int eierid){

        System.out.println("Epost hentet, iden var " + eierid);
        System.out.println(rep.hentEnEmail(eierid));
        return rep.hentEnEmail(eierid);
    }

    @GetMapping("/hentHoyestBrukerID")
    public String hentHoyesteBrukerIDCon(){
        System.out.println("Den høyeste brukerID-en er " + rep.hentHoyesteBrukerID());
    return rep.hentHoyesteBrukerID();
    }

}
