/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class Zadanie {
    private static EntityManagerFactory emf =  Persistence.createEntityManagerFactory("zadaniePU");;
    
//    public Zadanie(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
//    }
    static int pocet(String nazov){
        int count = 0;
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Prednaska.countAll");
        try {
            count = (int) q.getSingleResult();
        } catch (Exception e) {
        }
        return count;
    }
    
    static boolean maprednasku(String akcia, String autor){
        Akcia a = new Akcia();
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Akcia.findByName")
                .setParameter("nazov", akcia);
        List <Akcia> konferencie = q.getResultList();
        for(Akcia current : konferencie){
            List <Prednaska> prednasky = current.getPrednasky();
            for(Prednaska p : prednasky){
                if(p.getAutori().contains(autor)){
                    return true;
                }
            }
        }
        
        return false;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int r = Zadanie.pocet("A2");
        System.out.println("pocete je " + r);
//        assertTrue("#A2 " + r, r==0);
    }

    
}
