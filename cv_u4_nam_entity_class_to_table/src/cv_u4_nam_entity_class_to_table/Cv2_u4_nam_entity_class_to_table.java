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
 * triedy Projekt2 pridajte nové metódy, ktoré pomocou NativeQuery vyhľadávajú rôzne informácie o osobách, napr.:

    int countAll() ktorá zistí a vráti počet osôb v DB

    int countNoNamed() ktorá zistí a vráti počet osôb bez mena

    Person theBig() zistí a vráti najťazšiu osobu (ak neexistuje null) 
 *
 * @author nwteor dorobit doc
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
        cv.showAllPersons();
        Long insertedId = cv.addPerson("homobix_main");
        System.out.println("inserted Id :|:---->> " + insertedId);
        //uloha 6: vyhladanie niektorej z osôb podľa jej id. Vyskúšajte zadať exitujúce aj neexistujúce id
        Person p = cv.findPerson(1l);
        if(p!= null){
            System.out.println("\n ul6: hladana ososba: " + p.toString());
        }
        //neexistujuce ID -> Exception in thread "main" javax.persistence.NoResultException: getSingleResult() did not retrieve any entities.
        //osetric exception
        p = cv.findPerson(Long.MIN_VALUE);
        if(p!= null){
            System.out.println("\n ul6: hladana ososba NEEXISTUJUCA: " + p.toString());
        }
        // TEST NULL VALUE THROW EXCEPTIONS
        p = cv.findPerson(null);// OK CATCHED 
        // java.lang.IllegalArgumentException BAD ID 
        // POCITAJ OSOBY 
        System.out.println("poCET KniH " + cv.countAll());
        System.out.println("poCET KniH bez mena " + cv.countNoNamed());
        //NAJDI NAJTAZSIU
        p = cv.theBig();
        if(p != null){
            System.out.println("\n NAJTAZSIA ");
            System.out.println(" tato"+ p.toString());
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
        //native  query v Person, trebalo pridat Person.class
        Query q = em.createNativeQuery("select * from T_OSOBA", Person.class);
        List<Person> result = (List<Person>) q.getResultList();
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
        Long id = -1l;
         try {
             //NEPOUZIT PERSIST dorobit... hento nejde ( get id - LAST_INSERT_ID())  tak iba takto 
             em.getTransaction().begin();
             Person newPerson = new Person(meno);
//             String query = "insert into T_OSOBA (meno) values('" + meno + "')";
//             String query_last_id = "SELECT LAST_INSERT_ID()'";
//             Query q = em.createNativeQuery(query);
//             q.executeUpdate();
             

             em.persist(newPerson);
             em.getTransaction().commit();
             id = newPerson.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
         finally{
             return id;
         }
    }

    //Do triedy Projekt2 pridajte metódu Person findPerson(Long id) , ktorá pomocou metódy entity-managera find v DB vyhľadá osobu podľa id.
    Person findPerson(Long id) {
        Person finded = null;
        
        try {
            finded = em.find(Person.class, id);
        } catch (java.lang.IllegalArgumentException e ) {
            System.out.println(" \n  B A D  I D!!!!");
        } 
        
        return finded;//delete
    }
    
    int countAll(){
        Query q = em.createNativeQuery("select count(*) from T_OSOBA");
        return (int) q.getSingleResult();
    }
    
    int countNoNamed() {
        int total = countAll();
        Query q = em.createNativeQuery("select count(MENO) from T_OSOBA");
        return total - (int) q.getSingleResult();
//        SELECT COUNT( NVL( column, 0 ) )
//      FROM table
//      WHERE column IS NULL;
    }
    
    Person theBig(){
        Person most_weighted = null;
        Query q = em.createNativeQuery("SELECT * \n" +
            "FROM T_OSOBA \n" +
            "WHERE vaha = ( select MAX(vaha) FROM T_OSOBA)"
        , Person.class);
        
        most_weighted = (Person) q.getResultList().get(0);// todo get random
        return most_weighted;
    }

}
