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
import javax.persistence.TypedQuery;

/**
 * <ol>
 *  <li><b> u1_vypisOs </b> - u1 - vypisanie vsetkych osob </li>
 *  <li><b> findByMeno  </b> - u1 - vyhľadá osobu podľa mena.  </li>
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
        // TODO code application logic here
        u1_vypisOs();
        findByMeno("gargamel451");
        
    }
    
    static void  u1_vypisOs(){
        
        TypedQuery<Osoba> all = em.createNamedQuery("Osoba.findAll", Osoba.class);
           for (Osoba o: all.getResultList()) {
               System.out.println(o.toString());
           }
        
    }
    
    static Osoba findByMeno(String meno){
        Osoba o = (Osoba)em.createNamedQuery("Osoba.findByMeno", Osoba.class);
        
        return o;
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
    
}
