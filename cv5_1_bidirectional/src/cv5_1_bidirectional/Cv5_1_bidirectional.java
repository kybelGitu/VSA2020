/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5_1_bidirectional;

import entities.*;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *<ol>
 * <li><b>rozdiel medzi jednosmernymi a obojsmernymi asociaciami</b></li>
 *  <ul><li>a, biderctional</li>
 * <li>b, so sipkami</li>
 * </ul>
 * </ol>
 * @author vsa
 */
public class Cv5_1_bidirectional {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv5_1_bidirectionalPU");
        private static EntityManager em;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        em = emf.createEntityManager();
        //create 2 subjects with one lector
        //PART a
        Predmet p1 = new Predmet();
        Predmet p2 = new Predmet();
        p1.setNazov("mat");
        p2.setNazov("fyz");
        //create lector
        OsobaCV o = new OsobaCV();
        o.setMeno("gagos");
        o.setPrednasky(new ArrayList<Predmet>());
        o.getPrednasky().add(p2);
        o.getPrednasky().add(p1);
        //bidirectional
        p1.setPrednasajuci(o);
        p2.setPrednasajuci(o);
        try {
            em.persist(o);
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("prednasajuci from subjects: " + p1.getPrednasajuci().toString() + " second: " + p2.getPrednasajuci().toString());
        //PART b
        //N-predmet:1-Garant,   1-prednasatel: N-Predmet
        OsobaCV1B garant = new OsobaCV1B();
        OsobaCV1B prednasatel = new OsobaCV1B();
        
        garant.setMeno("somGranatMore");
        prednasatel.setMeno("digJaPrednasatel");

        PredmetCV1B p1CV1B = new PredmetCV1B();
        PredmetCV1B p2CV1B = new PredmetCV1B();
        p1.setNazov("zem");
        p2.setNazov("des");
        //set garant to predmet
        p1CV1B.setGarant(garant);
        p2CV1B.setGarant(garant);
        //set prednasky to lector
        prednasatel.setPrednasky(new ArrayList<PredmetCV1B>());
        prednasatel.getPrednasky().add(p2CV1B);
        prednasatel.getPrednasky().add(p1CV1B);
        //results
        try {
//cascade: prednasatel_
//                     |-> predmet1, predmet2_
//                                            |->Garant
            em.persist(prednasatel);
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception e) {
        }
        System.out.println("predmet:  " + p1CV1B.toString() + "garant " + p1CV1B.getGarant().toString());
    }
}
