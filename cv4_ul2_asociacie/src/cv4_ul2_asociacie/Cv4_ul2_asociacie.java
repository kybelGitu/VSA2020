/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv4_ul2_asociacie;

import controllers.*;
import entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Cv4_ul2_asociacie {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv4_ul2_asociaciePU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vydavatelstvo_cv4_ul2 FF = new Vydavatelstvo_cv4_ul2();
        //create books
        FF.setAdresa("jana karla 321");
        Kniha_cv4_ul2 biblia = new Kniha_cv4_ul2();
        biblia.setNazov("biblia");
        Kniha_cv4_ul2 biblia2 = new Kniha_cv4_ul2();
        biblia2.setNazov("biblia ina");
//        save books to DB (with controller)
        Kniha_cv4_ul2JpaController KnihaCntrl = new Kniha_cv4_ul2JpaController(emf);
        KnihaCntrl.create(biblia2);
        KnihaCntrl.create(biblia);
        //save books to list
        List <Kniha_cv4_ul2> kList = new ArrayList<Kniha_cv4_ul2>();
        //add autors
        Osoba_cv4_ul2 o1 = new Osoba_cv4_ul2();
        Osoba_cv4_ul2 o2 = new Osoba_cv4_ul2();
        o1.setMeno("zlatos");
        o2.setMeno("bokos");
        //assign autores to books  
        List autors = new ArrayList<Osoba_cv4_ul2>();
        autors.add(o1);
        autors.add(o2);
        //create controller for autores
        Osoba_cv4_ul2JpaController osobaCntrl = new Osoba_cv4_ul2JpaController(emf);
        //add autors to DB
        osobaCntrl.create(o2);
        osobaCntrl.create(o1);
        biblia.setAutor(autors);
        biblia2.setAutor(autors);
        try {
            //save to db
            KnihaCntrl.edit(biblia2);
        } catch (Exception ex) {
            Logger.getLogger(Cv4_ul2_asociacie.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            KnihaCntrl.edit(biblia);
        } catch (Exception ex) {
            Logger.getLogger(Cv4_ul2_asociacie.class.getName()).log(Level.SEVERE, null, ex);
        }
        //add list of books to vydavatel
        FF.setPublikacia(kList);
        Vydavatelstvo_cv4_ul2JpaController vydavatelstvoCntrl = new Vydavatelstvo_cv4_ul2JpaController(emf);
        vydavatelstvoCntrl.create(FF);
        
    }

}
