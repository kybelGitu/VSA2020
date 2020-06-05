/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_entity_fromdb;

import entities.Osoba;
import entities.Osoba_;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Test_entity_fromDB {
       static  EntityManagerFactory emf;
        static EntityManager em ;

    public Test_entity_fromDB() {
         emf = Persistence.createEntityManagerFactory("test_entity_fromDBPU");
         em = emf.createEntityManager();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Test_entity_fromDB cv = new Test_entity_fromDB();
        em.getTransaction().begin();
        Osoba o = new Osoba();
        o.setMeno("bez manea");
        em.persist(o);
        em.getTransaction().commit();
    }

    public void persist(Object object) {


    }
    
    

    
}
