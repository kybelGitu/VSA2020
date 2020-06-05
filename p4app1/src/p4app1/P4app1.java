/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p4app1;

import entities.Kniha;
import entities.Osoba;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author igor
 */
public class P4app1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p4app1PU");
        EntityManager em = emf.createEntityManager();
        Osoba o1 = new Osoba();
        o1.setMeno("Kernighan");

        Osoba o2 = new Osoba();
        o2.setMeno("Ritchie");
        
        Kniha k = new Kniha();
        k.setNazov("The C language");
        k.setAutor(new ArrayList());
        k.getAutor().add(o1);
        k.getAutor().add(o2);
        
        em.persist(o1);
        em.persist(o2);
        em.persist(k);
        // pozor! ak asociovane osoby este neexistuju, musia byt vytvorene v DB s knihou v jednej transakcii.
        em.getTransaction().begin();
        em.getTransaction().commit();        
        em.close();
    }   
}
