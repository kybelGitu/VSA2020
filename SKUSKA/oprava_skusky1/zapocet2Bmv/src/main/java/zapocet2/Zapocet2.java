package zapocet2;

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
//        persist(new Osoba());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zapocet2PU");
        EntityManager em = emf.createEntityManager();

        novyPredmet(em, "OOP", "Hrach");
        novyPredmet(em, "VSA", "Mrkva");
        novyPredmet(em, "ASOS", "Mrkva");
        Zapocet2.novyPredmet(em, "P4", "Igor");
        System.out.println("Mrkvov uvazok: " + pocetPrednasok(em, "Mrkva"));    // vypise 2  

        Osoba vyuc = prednasajuci(em, "VSA");
        System.out.println("Prednasajuci VSA: " + vyuc);
    }

    /* Vrati profesora prednasajuceho predmet s danym kodom
     * Ak kod nie je zadany alebo predmet s danym kodom neexistuje vrati null.
     */
    public static Osoba prednasajuci(EntityManager em, String kodPredmetu) throws Exception {
        if (kodPredmetu == null || kodPredmetu == "") {
            return null;
        }
        Query q = em.createNamedQuery("predmet.findByKod");
        q.setParameter("kod", kodPredmetu);
        try {
            Predmet p = (Predmet) q.getSingleResult();
            return p.getProfesor();
        } catch (Exception e) {
            return null;
        }
    }

    /* Vrati pocet predmetov, ktore prednasa osoba so zadanym menom. 
     * Ak meno nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPrednasok(EntityManager em, String meno) throws Exception {
        if (meno == null || meno == "") {
            return 0;
        }
        try {
            Query q = em.createNamedQuery("osoba.findByMeno");
            q.setParameter("meno", meno);
            Osoba o = (Osoba) q.getSingleResult();
            return o.getPrednasky().size();

        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            return 0;
        }

    }

    /*Vytvori novy predmet.
     *
     * @param em            entity manager
     * @param kodPredmetu   kod predmetu
     * @param meno          meno profesora ktory predmet prednasa
     *
     * Metoda naprv zisti ci predmet s danym kodom uz neexistuje.
     *   ak existuje, nerobi nic viac ale vrati false.
     *
     * Ak predmet s danym kodom neexistuje, vytvori ho, pricom rocnik bude "BC-1".
     * Nasledne, ak je meno prednasajuceho zadane, vyhlada osobu s danym menom. 
     *   Ak osoba v DB neexistuje vytvori ju, pricom datum narodenia nebude zadany (ostane null) 
     * Nakoniec nastavi tuto osobu ako profesora, ktory prednasa novy predmet.
     *   Pozn. ak meno nebolo zadane, prednasajuci profesor ostane null.   
     *   Pozn. metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne.
     *
     * Navratova hodnota: 
     *   false: ak predmet uz existoval alebo kod predmetu nebol zadany. . 
     *   true:  inak. 
     */
    public static boolean novyPredmet(EntityManager em, String kodPredmetu, String meno) throws Exception {
        boolean result = false;
        if (kodPredmetu == null || kodPredmetu == "") {
//            kod predmetu nebol zadany. 
            return false;
        }
        try {

            Query q = em.createNamedQuery("predmet.findByKod");
            q.setParameter("kod", kodPredmetu);
            Predmet p = new Predmet();
            try {
                //predmet exists, end
                p = (Predmet) q.getSingleResult();
                return false;
            } catch (NoResultException e) {
                em.getTransaction().begin();
                p.setKod(kodPredmetu);
                if (meno != null) {
                    Query q2 = em.createNamedQuery("osoba.findByMeno");
                    q2.setParameter("meno", meno);
                    Osoba o = new Osoba();
                    try {
                        o = (Osoba) q2.getSingleResult();
                    } catch (NoResultException e2) {
                        o.setMeno(meno);
                        em.persist(o);
                    } catch (Exception e2) {
//                        em.close();
                        System.out.println("exception osoba " + e.getMessage());
                        return false;
                    }
                    o.getPrednasky().add(p);
                    p.setProfesor(o);
                }
                em.persist(p);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("exception by get predmet ---> " + e.getLocalizedMessage() +'\n' );
                System.err.println("exception by get predmet ---> " + e.getMessage()+'\n' );
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

}
