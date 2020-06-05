package zapocet2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence; 
import javax.persistence.Query;
public class Zapocet2 {

    /**
     * @param args the command line arguments
     *
     * Len pre vase testovanie. Mozete si upravit.
     */
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zapocet2PU");
        EntityManager em = emf.createEntityManager();

        pridajVyucujuceho(em, "Hrach", "OOP");
        pridajVyucujuceho(em, "Mrkva", "VSA");
        pridajVyucujuceho(em, "Mrkva", "ASOS");
        pridajVyucujuceho(em, "Mrkva", "ASOS");

        System.out.println("Mrkvov uvazok: " + pocetPredmetov(em, "Mrkva"));    // vypise 2  
        
        Set<Osoba> z1= vyucujuci(em, "VSA");
        System.out.println("Pocet vyucujucich VSA: " + z1.size());              // vypise 1
    }

    /* Vrati zoznam vyucujucich predmetu so zadanym kodom
     * Ak kod nie je zadany alebo predmet s danym kodom neexistuje vrati null.
     */
    public static Set<Osoba> vyucujuci(EntityManager em, String kodPredmetu) throws Exception {
        if(kodPredmetu == null){
            return null;
        }
        Predmet p = new Predmet();
        //create query
        Query q = em.createNamedQuery("Predmet.findByKod")
                .setParameter("kod", kodPredmetu);
        try {
            p = (Predmet) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return p.getVyucujuci();
    }

    /* Vrati pocet predmetov, ktore vyucuje osoba so zadanym menom. 
     * Ak meno nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPredmetov(EntityManager em, String meno) throws Exception {
//        throw new Exception("NEIMPLEMENTOVANE");
        int result = 0;
        List <Osoba> o = new ArrayList<Osoba>();
        Query q = em.createNamedQuery("Osoba.findByMeno").setParameter("meno", meno);
        try {
            o = q.getResultList();
            for(Osoba os : o){
                result += os.getPredmety().size();
            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    /* Prida predmetu vyucujuceho:
     *
     * @param em            entity manager
     * @param meno          meno vyucujuceho
     * @param kodPredmetu   kod predmetu
     *
     * Metoda vyhlada osobu podla mena a predmet podla kodu. 
     *   Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     * Ak osoba s danym menom v DB neexistuje vytvori ju, pricom 
     *   datum narodenia nebude zadany (ostane null) 
     * Ak predmet s danym kodom neexistuje, vytvori ho, pricom rocnik bude "BC-1". 
     * Nasledne zaradi osobu medzi vyucujucich predmetu. 
     *
     * Navratova hodnota: 
     *   false: ak niektory z argumentov funkcie bol null. 
     *   true:  inak. 
     */
    public static boolean pridajVyucujuceho(EntityManager em, String meno, String kodPredmetu) throws Exception {
//        throw new Exception("NEIMPLEMENTOVANE");
        Osoba o = new Osoba();
//            o.setId(kodPredmetu);
        Predmet p = new Predmet();
        try {
            p = new Predmet();
            Query q = em.createNamedQuery("Predmet.findByKod").setParameter("kod", kodPredmetu);
            p = (Predmet) q.getSingleResult();
        } catch (NoResultException e) {
            //create new predmet
            p.setKod(kodPredmetu);
            p.setRocnik("BC-1");
            p.setVyucujuci(new HashSet<Osoba>());
            em.persist(p);
        }catch(Exception e){
            
        }
        Query q = em.createNamedQuery("Osoba.findByMeno").setParameter("meno", meno);
        try {
            o = (Osoba) q.getSingleResult();

        } catch (Exception e) {
            o.setMeno(meno);
            o.setPredmety(new HashSet<Predmet>());
        }

        o.getPredmety().add(p);
        p.getVyucujuci().add(o);
        em.persist(o);
        em.getTransaction().begin();
        em.getTransaction().commit();

        return true;
    }

}
