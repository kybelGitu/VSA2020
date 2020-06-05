/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr5_asociacie;

import entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class Pr5_asociacie {
//    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pr5_asociaciePU");
//    private  static EntityManager em;

    /**
     * @param args the command line arguments
     */

public static void create() {
        Osoba o1 = new Osoba();
        o1.setMeno("Hopcroft");

        Osoba o2 = new Osoba();
        o2.setMeno("Ullman");

        Osoba o3 = new Osoba();
        o3.setMeno("Aho");

        Kniha k1 = new Kniha();
        k1.setId(333L);
        k1.setNazov("Uvod do teorie automatov");

        Kniha k2 = new Kniha();
        k2.setNazov("Algoritmy a datove struktury");
        //prednaska
//        o1.setDielo(new ArrayList<>());
//        o1.getDielo().add(k1);
//        o1.getDielo().add(k2);
//
//        o2.setDielo(new ArrayList<>());
//        o2.getDielo().add(k1);
//        o2.getDielo().add(k2);
//
//        o3.setDielo(new ArrayList<>());
//        o3.getDielo().add(k2);
        //inicializujte zoznam na len druhej strane asociácie, t.j. zoznam autorov v triede kniha. 
//        k1.setAutor(new ArrayList<>());
//        k1.getAutor().add(o1);
//        k1.getAutor().add(o2);
//        
//        k2.setAutor(new ArrayList<>());
//        k2.getAutor().add(o3);
//        k2.getAutor().add(o2);
//inicializujte obe strany asociácie, t.j. zoznam diel v triede osoba aj zoznam autorov v triede kniha.
//Dajte pozor aby do zoznamu diel pridali naozaj len tie diela, ktorych je daná osoba autorom! 
        o1.setDielo(new ArrayList<>());
        k1.setAutor(new ArrayList<>());
        o2.setDielo(new ArrayList<>());
        k2.setAutor(new ArrayList<>());
        o1.getDielo().add(k1);
        k1.getAutor().add(o1);
//inicializujte obe strany asociácie. Tentoraz však urobte pri vypĺňaní zoznamov úmyslene chybu:
//Do zoznamu autorov knihy dajte osobu, ktorá nie je jej autorom. Príp. naopak do zoznamu diel osoby dajte knihu, ktorej táto osoba nie je autorom. 
        k1.getAutor().add(o3);//chyba
        
        o1.getDielo().add(k2);
        k2.getAutor().add(o1);
        
        k1.getAutor().add(o2);
        o2.getDielo().add(k1);

        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pr5_asociaciePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {

            em.persist(k1);
            em.persist(k2);
            em.persist(o1);
            em.persist(o2);
            em.persist(o3);

            em.getTransaction().commit();
            em.clear();

            System.out.println("kniha->autori size: " + k1.getAutor().size());
            System.out.println("autor->knihy size: " + o1.getDielo().size());
            
            Query q = em.createNamedQuery("Kniha.findAll");
            List <Kniha> knihy = q.getResultList();
            
            q = em.createNamedQuery("Osoba.findAll");
            List <Osoba> osoby = q.getResultList();
            
            System.out.println("pocet osob: " + osoby.size() + " Pocet knih: " + knihy.size());
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public static void read() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pr5_asociaciePU");
        EntityManager em = emf.createEntityManager();
        
        Kniha k = em.find(Kniha.class, 333L);
        System.out.println(k.getNazov());
        System.out.println(k.getAutor().size());
    }
    public static void main(String[] args) {
        // TODO code application logic here
//        em= emf.createEntityManager();
        create();
        read();
        
    }

    
    
}
