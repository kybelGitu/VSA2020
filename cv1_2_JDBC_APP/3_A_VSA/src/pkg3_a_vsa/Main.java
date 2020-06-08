/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3_a_vsa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3_A_VSAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Kniha k = em.find(Kniha.class, 1L);
        k.setCena(15.0);
        
        Kniha k1 = em.find(Kniha.class, 1L);
        k1.setCena(k1.get);
        em.getTransaction().commit();;
        
//        try {
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }

}
