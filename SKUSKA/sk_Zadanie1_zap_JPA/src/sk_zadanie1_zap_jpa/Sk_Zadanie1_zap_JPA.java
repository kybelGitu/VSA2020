/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk_zadanie1_zap_jpa;

import entities.KNIHA;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Peter
 */
public class Sk_Zadanie1_zap_JPA {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sk_Zadanie1_zap_JPAPU");
    private  static EntityManager em = emf.createEntityManager();
    
    public static Set <KNIHA> citaj(String PATH){
        BufferedReader br = null;
        try {
            Set<KNIHA> data = new  HashSet<KNIHA>();
            String line;
            br = new BufferedReader(new FileReader(PATH));
            try {
                while ((line = br.readLine()) != null) {
                    KNIHA k = new KNIHA();
                    String s[] = line.split(";");
                    if (s.length < 3) {
                        System.out.println("kratky riadok");
                        return null;
                    }
                    k.setNazov(s[0].trim()); 
                    k.setAutor(s[1].trim()); 
                    try {
                        k.setPocet(Integer.parseInt( s[2].trim())); 
                    } catch (NumberFormatException e) {
                        k.setPocet( Integer.MIN_VALUE); 
                    }
                    try {
                        data.add(k);
                    } catch (IllegalArgumentException e) {
                        System.out.println("data su uz tam");
                    }
                }
                return data;/**
                 * @param args the command line arguments
                 */
            } catch (IOException ex) {
                Logger.getLogger(Sk_Zadanie1_zap_JPA.class.getName()).log(Level.SEVERE, null, ex);
            }
 /**
             * @param args the command line arguments
             */
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sk_Zadanie1_zap_JPA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Sk_Zadanie1_zap_JPA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public static void uloz(Set <KNIHA> data){
        em.getTransaction().begin();
        for(KNIHA k : data) {
            em.persist(k);
        }
        em.getTransaction().commit();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Set<KNIHA> KN = citaj("C:\\skola\\FEI6.semester_III\\Vývoj softvérového inžinierstva 2020\\SHARED_FOLDER\\SKUSKA\\sk_Zadanie1_zap_JPA\\src\\data\\data1.csv");
        uloz(KN);
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sk_Zadanie1_zap_JPAPU");
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
