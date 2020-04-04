/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv_u4_nam_entity_class_to_table;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 ** TODO: a dalšie rozšírenie pre najvytrvalejších...+ ZAPOCET Z MINULEHO ROKA
 ** <a href='http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV2'>http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV2</a>
 *
 * @author vsa dorobit doc
 * 
 * 
 */
public class Cv2_u4_nam_entity_class_to_table {

    private static EntityManager em;

    /**
     * @param args the command line arguments
     */
    /////////////*************C O N S T R U C T O R S *************\\\\\\\\\\\\

    /**
     *
     */
    public Cv2_u4_nam_entity_class_to_table() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv2_u4_nam_entity_class_to_tablePU");
        this.em = emf.createEntityManager();

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Cv2_u4_nam_entity_class_to_table cv = new Cv2_u4_nam_entity_class_to_table();
        Long insertedId = cv.addPerson("homobix");
        System.out.println("inserted Id :|:---->> " + insertedId);
        //uloha 6: vyhladanie niektorej z osôb podľa jej id. Vyskúšajte zadať exitujúce aj neexistujúce id
        Person p = cv.findPerson(151l);
        System.out.println("ul6: hladana ososba: " + p.toString());
        //neexistujuce ID -> Exception in thread "main" javax.persistence.NoResultException: getSingleResult() did not retrieve any entities.
        //osetric exception
        p = cv.findPerson(Long.MIN_VALUE);
        if(p!= null){
            System.out.println("ul6: hladana ososba: " + p.toString());
        }
        
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
        em.getTransaction().begin();
        //named query v Person
        Query q = em.createNamedQuery("Person.findAll");
        List<Person> result = q.getResultList();
//        System.out.println("length of list " +  result.size());
        for (Person p : result) {
//            int age = p.getAge();
            System.out.println("osoba: " + p.toString());
//            System.out.println("a ma " + age + " rokov ");
        }
        em.getTransaction().commit();
//        em.close();
    }

    Long addPerson(String meno) {
        //NEPOUZIT PERSIST dorobit
        em.getTransaction().begin();
        Person newPerson = new Person(meno);
        Query q = em.createNamedQuery("Person.insertPerson");
        q.getParameter(meno);
        em.getTransaction().commit();
        return newPerson.getId();
//        
//        
//        return null;//todo: delete
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

}
