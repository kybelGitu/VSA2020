/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cv3_jpql;

import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 ** TODO: a dalšie rozšírenie pre najvytrvalejších...+ ZAPOCET Z MINULEHO ROKA
 ** <a href='http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV2'>http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV2</a>
 *
 * <ol>
     * <li> <b>uloha 1 </b>
         * <ol>
             * <li> findAll (show all persons) - vyselektuju vsetky osoby </li>
             * <li> findByMeno  - vyhlada podla mena </li>
             * <li> countAll - spocita osoby </li>
         * </ol>
     * </li>
      * <li> <b>uloha 2  </b> - uloha 2/6 cez JPQLQuery
         * <ol>
             * <li>  show all persons -  pouziva findAll z ulohy 1</li>
             * <li>  addPerson -  asi ako minule, vytvory zaznam vrati kluc </li>
             * <li> countNoNamed - vrati pocet osob bez mena </li>
             * <li> theBig - vrati najtazsiu osobu </li>
         * </ol>
     * </li>
 * </ol>
 * 
 * @author vsa dorobit doc
 * 
 */
public class Cv3_jpql {

    private static EntityManager em;
    
     /**
     
     * @param args
     */
    public static void main(String[] args) {
        Cv3_jpql cv = new Cv3_jpql();
        System.out.println("ULOHA 1");
        System.out.println("showing all curents persons");
        cv.showAllPersons();
        System.out.println("searching person with id 201");
        Person p = cv.findPerson(201l);
        try {
                    System.out.println("osoba hladana " + p.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
        System.out.println("searching person by name");
        System.out.println("1. no duplicate values");
        p = cv.findByMeno("hekotpors");
        try {
                    System.out.println("osoba hladana " + p.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p = cv.findByMeno("neni som v db");
        try {
                    System.out.println("osoba hladana " + p.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("pocet osob : " + cv.countAll());
        
        System.out.println("ULOHA 2");
        
        Long newId = cv.addPerson("new osoba homokus");
        if(newId != null){
            System.out.println(" new id of new person " + newId);
        }
        System.out.println("count of unnamed names " + cv.countNoNamed() );
        System.out.println("the bigest  " + cv.theBig());
    }

    /**
     * @param args the command line arguments
     */
    /////////////*************C O N S T R U C T O R S *************\\\\\\\\\\\\

    /**
     *
     */
    public Cv3_jpql() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv2_u4_nam_entity_class_to_tablePU");
        this.em = emf.createEntityManager();
    }

   

    /**
     *
     * v triede <strong>Projekt2</strong> implementujte metódu
     * <strong>showAllPersons(EntityManager em)</strong>,ktorá pomocou
     * <strong>nativeQuery</strong> vyhľadá všetky osoby a vypíše údaje o nich
     * na štandardný výstup. Entity-manager dostane metóda ako argument. Na
     * výpis údajov o osobe využite metódu toString.
     *
     * @param em Entity Manager
     */
    void showAllPersons() {
        System.out.println(" VYPISANIE OSUOB");
//        em.getTransaction().begin(); ASI NEMUSI BYT!!!!
        //named query v Person
        TypedQuery<Person> q = em.createNamedQuery("Person.findAll", Person.class);
        
        for (Person o: q.getResultList()) {
            System.out.println(o.toString());
        }
    }



    //Do triedy Projekt2 pridajte metódu Person findPerson(Long id) , ktorá pomocou metódy entity-managera find v DB vyhľadá osobu podľa id.
    Person findPerson(Long id) {
        Person finded = null;
        em.getTransaction().begin();
        //named query v Person
        Query q = em.createNamedQuery("Person.findById");
        q.setParameter("id", id);
        try {
            finded = (Person) q.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            System.out.println("osoba s id " + id + " nie je v DB");
        }
        em.getTransaction().commit();
        return finded;//delete
    }
    
    Person findByMeno(String meno) {
        Person finded = null;
        em.getTransaction().begin();
        //named query v Person
        Query q = em.createNamedQuery("Person.findByMeno");
        q.setParameter("meno", meno);
        try {
            finded = (Person) q.getSingleResult();
        }
        catch (NonUniqueResultException e){
            System.out.println(" PERSON NENI UNIKATNY!!! berem prveho...");
            finded =  (Person) q.getResultList().get(0);
        }
        catch (javax.persistence.NoResultException e) {
            System.out.println("osoba s menom " + meno + " nie je v DB");
        }
        em.getTransaction().commit();
        return finded;//delete
    }
    
    // uloha 2
        Long addPerson(String meno) {
        em.getTransaction().begin();
        Person newPerson = new Person(meno);
        em.persist(newPerson);
//        pri priebeznom davkovani, teraz netreba (ide aj stym)
        em.flush();
        em.getTransaction().commit();
        return newPerson.getId();
//        
//        
//        return null;//todo: delete
    }
        
    int countAll() {
        Query q = em.createNamedQuery("Person.countAll");
        try {
            int result = Math.toIntExact((Long)q.getSingleResult()) ;
                return result;
            
        } catch (Exception e) {
            return Integer.MIN_VALUE;
        }
    }
    int countNoNamed() {
        Query q = em.createNamedQuery("Person.countNoNamed");
        try {
            int result = Math.toIntExact((Long)q.getSingleResult()) ;
                return result;
            
        } catch (Exception e) {
            return Integer.MIN_VALUE;
        }
    }
    
    Person theBig(){
        Person heavy = null;
        Query q = em.createNamedQuery("Person.theBig");
        try {
            heavy = (Person) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            System.out.println(" JE VIAC NAJTAZSICH !!! berem prveho...");
            heavy =  (Person) q.getResultList().get(0);
        }
        catch (NoResultException e){
            System.out.println("nieje mozne rozhodnut pozri null hodnoty");
        }
        
        return heavy;
    }
    

}
