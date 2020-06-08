/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3_b_vsa_cv;

import entities.Kniha;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
public class Main {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kniha K = new Kniha();
        K.setIsbn("aaa");
        persist(K);
        
        Kniha naj = najlacnejsia();
        naj.toString();
        
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3_B_VSA_min_zapocetPU");
        EntityManager em = emf.createEntityManager();
        
        em.close();

    }
    public   Kniha najlacnejsia(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3_B_VSA_min_zapocetPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery tq = em.createQuery("select k from Kniha k", Kniha.class);
        List <Kniha> knihy = tq.getResultList();
        
        double max = Double.MAX_VALUE;
        Kniha ret = null;
        for(Kniha k : knihy) {
            if(k.getCena()<= max){
                ret = k;
                max = k.getCena();
            }
        }
//        em.getTransaction().begin();
        return ret;
    }
    
}
