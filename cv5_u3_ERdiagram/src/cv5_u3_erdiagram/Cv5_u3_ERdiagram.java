/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5_u3_erdiagram;

import entities.Osoba_3u;
import entities.Predmet_3u;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Cv5_u3_ERdiagram {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv5_u3_ERdiagramPU");
    private static EntityManager em;
    
    public void create(){
        em= emf.createEntityManager();
        
        Osoba_3u o1 = new Osoba_3u();
        Predmet_3u p_o1 = new Predmet_3u();
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }



    
    
}
