/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2a_vsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class Pr2A_VSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("pr2A_VSAPU");
        EntityManager em = emf.createEntityManager();//praca S DB
        
        em.getTransaction().begin();
        Query q = em.createNativeQuery("select * from KNIHA_PR", Kniha.class);
        List <Kniha> knihy= q.getResultList();
    
        for(Kniha k : knihy){
            System.out.println("kniha" +  k);
            k.setCena(k.getCena() * 0.9);

        }
        
        em.getTransaction().commit();

//    public void persist(Object object) {
//     
//    }
    
        


    }
    
}
