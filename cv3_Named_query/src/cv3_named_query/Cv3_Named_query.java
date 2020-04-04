/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3_named_query;

import entities.Osoba;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * <ol>
     * <li>uloha
     *  <ol>
     *   <li><b> u1_vypisOs </b> - u1 - vypisanie vsetkych osob </li>
     *   <li><b> findByMeno  </b> - u1 - vyhľadá osobu podľa mena.  </li>
     *  </ol>
     * </li>
     * <li>
     *  <ol>
     *      <li>
     * 
     *      </li>
     *  </ol>
     * </li>
 * </ol>
 * 
 * 
 * @author vsa
 * 

 * 
 */
public class Cv3_Named_query {

        static EntityManager em;
    
    Cv3_Named_query(){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv3_Named_queryPU");
         em = emf.createEntityManager();
    }
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //ULOHY 1
        // pre nastavenie EM treba pouzit constructor
        Cv3_Named_query cv3 = (new Cv3_Named_query());
        cv3.u1_vypisOs();
        Osoba hladana = findByMeno("gargamel451 '\n'");
        if(hladana != null)
            System.out.println("hladana osoba : '\n'"+ hladana.toString());
        
        cv3.setVahaIfNull();
        //Check if all values are changed
        cv3.u1_vypisOs();
        //at end close Entity Manager
        cv3.closeEM();

        
        
    }
    //************************ulohy - DEF ************************
     void  u1_vypisOs(){
        
        TypedQuery<Osoba> all = em.createNamedQuery("Osoba.findAll", Osoba.class);
           for (Osoba o: all.getResultList()) {
               System.out.println(o.toString());
           }
        
    }
    
    static Osoba findByMeno(String meno){
        Osoba hladana = null;
        TypedQuery<Osoba> TQ = em.createNamedQuery("Osoba.findByMeno", Osoba.class);
        TQ.setParameter("meno", meno);
        try {
            hladana = TQ.getSingleResult();
        } 
        catch (Exception e) {
            System.out.println("handled excception");
           e.printStackTrace();
            System.out.println("continued...");
        }
        
            
        return hladana;
    }
      
    void  setVahaIfNull(){
        em.getTransaction().begin();
        Query q   = em.createNamedQuery("Osoba.updateVaha");
        System.out.println("reslut q " + q.executeUpdate());
        em.getTransaction().commit();
    }

    /**
     *
     * @param object
     */
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv3_Named_queryPU");
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
    
    public void closeEM(){
        this.em.close();
    }
    
}
