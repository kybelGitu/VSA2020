package uloha1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Peter
 */
public class Uloha1 {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("uloha1PU");
    private static EntityManager em = emf.createEntityManager();
    
    public static void load(String csv){
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(csv));
            
            Set stuffSet = new HashSet<Tovar>();
            em.getTransaction().begin();
            while ((line = br.readLine()) != null) {
                String s[] = line.split(";");
                if(s.length == 2){
                    String nazov = s[0].trim();
                    Query q = em.createNativeQuery("select * from tovar where nazov ='" +nazov+"'" );
                    try {
                        q.getSingleResult();
                        System.out.println("duplicite VALUE !!!!!");
                        continue;
                    } catch (NoResultException e) {
                        Tovar newStuff = new Tovar();
                        newStuff.setNazov(nazov );
                        newStuff.setCena(Double.parseDouble(s[1]));
                        em.persist(newStuff);
                        stuffSet.add(newStuff);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IOException ex) {
            Logger.getLogger(Uloha1.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public static void testJPAObjects() {
        Tovar t = new Tovar();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        Query q = em.createNativeQuery("select * from tovar", Tovar.class);
        List<Tovar> l = q.getResultList();
        Tovar x2 = new Tovar();
        if (t == x2) {
            System.out.println("nevu objec t a prvy su rovnake");
        }
        else{
            System.out.println("novy a ten druhy su ine");
        }
        for(Tovar x : l){
            if(t==x){
                System.out.println("objekty su rovnake");
            }
            else{
                System.out.println("objekty su rovnake");
            }
            
                
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //        testJPAObjects();
        load("C:\\skola\\FEI6.semester_III\\Vývoj softvérového inžinierstva 2020\\SHARED_FOLDER\\SKUSKA\\sk_3cv_uloha1\\src\\uloha1\\data.csv");

        
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sk_3cv_uloha1PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
