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
     *   <li><b> setVahaIfNull </b>   </li>
     *   <li><b> addPerson </b>   </li>
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
        static EntityManagerFactory emf;
    
    Cv3_Named_query(){
         emf = Persistence.createEntityManagerFactory("cv3_Named_queryPU");
         em = emf.createEntityManager();
         
    }
    
    
    /**
     * @param args the command line arguments
     * working...Uloha2.
     */
    
    public static void main(String[] args) {
        //ULOHY 1
        // pre nastavenie EM treba pouzit constructor
        Cv3_Named_query cv3 = (new Cv3_Named_query());
        /*cv3.u1_vypisOs();
        Osoba hladana = findByMeno("gargamel451");
        if(hladana != null)
            System.out.println("hladana osoba : '\n'"+ hladana.toString());
        
        hladana = findByMeno("gargamel451_neexistujuci");
        if(hladana != null)
            System.out.println("hladana osoba : '\n'"+ hladana.toString());
        
        cv3.setVahaIfNull();
        //Check if all values are changed
        cv3.u1_vypisOs();*/
        //add new person (osoba) and get his id 
        Long idLong = cv3.addPerson("hekotpors");
        System.out.println("new ososbas id " + idLong);
        
        System.out.println("ADD NULL PERSON");
        idLong = cv3.addPerson(null);
        System.out.println("new ososbas id " + idLong);
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
            hladana = TQ.getResultList().get(0);
        } 
        catch (IndexOutOfBoundsException e) {
            System.err.println(" out of range element not found");
                
        }
        catch (Exception e){
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
//    public void persist(Object object) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv3_Named_queryPU");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
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
    Long addPerson(String meno){
        Osoba O = new Osoba();
        O.setMeno(meno);
        Long id = null;
        em.getTransaction().begin();
        try {
            em.persist(O);
            id = O.getId();
        } catch (Exception e) {
            System.out.println(" CANNOT PERSIST!!!");
            e.printStackTrace();
        }
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("COMMIT FAILS");
            e.printStackTrace();
        }
        return id;
    }
  /*  
    Long addPerson(String meno){
        //first create object osoba with em and then insert this osoba
        // to db, created osoba will be have setted data
        Long newId = null;
        Osoba O = new Osoba();
        O.setMeno(meno);
        em.getTransaction().begin();
        System.out.println("id osoba " + O.getId());
//            em.createNativeQuery("Insert into Osoba (id, meno,narodena,vaha) values (:id,:meno,?,?)")
//                .setParameter("id", O.getId())
//                .setParameter("meno",meno)
//                    .executeUpdate();
//        Query q = em.createNativeQuery("Insert into Osoba (id, meno,narodena,vaha) values ("+O.getId(),O.getMeno()+",?,?)");
//        q.executeUpdate();
        
                
        try {
             newId = O.getId();
        } catch (NullPointerException e) {
            System.out.println(" approached null pointer exxception at getting new id of OSoba");
        }
        
//        newId = O.getId();
//        Query q   = em.createNamedQuery("Osoba.LastInsertedId");
//        newId = (Long) q.getSingleResult();
        
        em.getTransaction().commit();
        
        return newId;
    }*/
    
    public void closeEM(){
        this.em.close();
        this.emf.close();
    }
    
}
