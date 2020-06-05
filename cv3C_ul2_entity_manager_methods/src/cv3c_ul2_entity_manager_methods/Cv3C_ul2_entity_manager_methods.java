/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3c_ul2_entity_manager_methods;

import entities.Osoba3cvUl2;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *<ol>
 * <li><b>Opakované volanie persist</b>
 * function <u>repeatCallPersist</u> zavola 2x persist a comitne naraz 
 * vlozi obidva zaznamy
 * </li>
 * <li><b>Opakované volanie persist s id</b> <u>repeatCallPersistNoAutoaa</u>
 * vyhodi rollback exception, transakcia sa nevykona
 * </li>
 * <li><b>persit a merge</b> function <u>persistAndMerge</u> id su duplicitne vyvolany ex
 * exception nic nie je vlozene
 * </li>
 * <li><b>clear</b>function <u>functionWithClear</u> - clear odstrani 
 * persistnuty objekt(nie flush)
 * </li>
 * <li><b>detach</b>function <u>functionWithDetach</u> - detach  
 * odstráni zadaný objekt z persistance kontextu 
 * </li>
 * <li><b>Opakované volanie find</b>function <u>repeatFind</u> 
 * pri hladanie prvkov s rovnakym id su objekty rovnake
 * </li>
 * <li><b>find a detach </b>function <u>findAndDetach</u> 
 * objekty find&detach a detach su rozne
 * </li>
 * 
 * </ol>
 * @author vsa
 */
public class Cv3C_ul2_entity_manager_methods {
//
    private static EntityManager em;
    private static EntityManagerFactory emf;
    //constructor
    public Cv3C_ul2_entity_manager_methods() {
        emf = Persistence.createEntityManagerFactory("cv3C_ul2_entity_manager_methodsPU");//male c -> chyba
        em = emf.createEntityManager();
    }
        
    void repeatCallPersist(){
    //        Začnite transakciu
        em.getTransaction().begin();
    //        Vytvorte inštanciu triedy Osoba - zadajte jej meno.
    //        Pozn. id nie je potrebné zadávať, prečo?
    // LEBO  JE NASTAVENE AUTOGEN (@GeneratedValue)
        Osoba3cvUl2 o = new Osoba3cvUl2();
        o.setMeno("meno");
    //Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké jej meno. 
        Osoba3cvUl2 o2 = new Osoba3cvUl2();
        o2.setMeno("meno");
//        pre obe inštancie zavolajte em.persist
        em.persist(o);
        em.persist(o2);
//        ukončite transakciu
        em.getTransaction().commit();
//Otázka: Koľko záznamov bude vytvorených v databáze? 
//Spustite program a overte si to. 
// OBIDVA ZAZNAMY ZAPISANE, ROZNE ID
    }
    void repeatCallPersistNoAuto() {
        try {
            em.getTransaction().begin();
//        Z entitnej triedy Osoba odstráňte anotáciu @GeneratedValue.
//        Osoba musí mať teraz zadanú aj hodnotu klúča id. 
//Vytvorte inštanciu triedy Osoba - zadajte id, meno a váhu
            Osoba3cvUl2 o1 = new Osoba3cvUl2();
            o1.setId(999L);
            o1.setMeno("cigan");
            o1.setNarodena(new Date());
//Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké id ale odlišné meno
            Osoba3cvUl2 o2 = new Osoba3cvUl2();
            o2.setId(999L);
            o2.setMeno("cigan2");
            o2.setNarodena(new Date());
//pre obe inštancie zavolajte em.persist
            em.persist(o1);
            em.persist(o2);
//        ukončite transakciu
            try {
                em.getTransaction().commit();//commit fail -> rollback exception is thrown
                //nikto nebude ulozeny java.sql.SQLIntegrityConstraintViolationException
            } catch (Exception e) {
                System.err.println(e.getCause());
//                em.getTransaction().rollback();//throw IllegalStateException
            }
                    
//Otázka: čo spraví program po spustení? Spustite program a overte si to. - hodi rollback exception
//TRANSAKCIA NIE JE UKONCENA NIKTO NIE JE ULOZENY
        } catch (Exception e) {
            e.printStackTrace();// catched IllegalStateException No transaction is currently active
            System.out.println("transaction is active");
//org.eclipse.persistence.internal.jpa.transaction.EntityTransactionImpl.rollback
        }
    }
    
    void persistAndMerge(){
//Vytvorte inštanciu triedy Osoba - zadajte id, meno a váhu
        EntityTransaction tx = em.getTransaction();
//        tx.begin(); if is transaction active throwed exception
        try {
            tx.begin();
            Osoba3cvUl2 o = new Osoba3cvUl2();
            o.setId(789l);
            o.setMeno("persistoAMergo");
            o.setVaha(4564.4d);
    //Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké id ale odlišné meno alebo váhu
            Osoba3cvUl2 o2 = new Osoba3cvUl2();
            o2.setId(789l);
            o2.setMeno("AMergoSecond");
            o2.setVaha(11.4d);
    //pre prvú inštanciu zavolajte em.persist
            em.persist(o);//o ma rovnake udaje ako o2
    //pre druhú inštanciu zavolajte em.merge
            em.merge(o2);
    //ukončite transakciu
            try {
                tx.commit();
            } catch (Exception e) {
                System.err.println(e.getMessage());//rollbackException duplicate IDs
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
//Otázka: čo spraví program po spustení? Spustite program a overte si to v DB. 
//transkacia rollback by exception duplicitne id, nic nie je vlozene 
    }
    //clear odstáni všetky objekty z persistance kontextu 
    void functionWithClear(){
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
//        Vytvorte inštanciu triedy Osoba - zadajte id a meno
            Osoba3cvUl2 o = new Osoba3cvUl2();
            o.setId(123l);
            o.setMeno("clearedName");
//Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké id ale odlišné meno
            Osoba3cvUl2 o2 = new Osoba3cvUl2();
            o2.setId(123l);
            o2.setMeno("clearedNameAnotherName");
//pre prvú inštanciu zavolajte em.persist
            em.persist(o);
//zavolajte em.clear
            em.clear();
//pre druhú inštanciu zavolajte em.persist
            em.persist(o2);
//ukončite transakciu
            try {
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
//Otázka: čo spraví program po spustení? Spustite program a overte si to v DB. 
//vlozi 2 objekt
        } catch (Exception e) {
            System.out.println("transaction currently active");
        }
    }
    
    void functionWithDetach(){
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
//Vytvorte inštanciu triedy Osoba - zadajte id a meno
            Osoba3cvUl2 o1 = new Osoba3cvUl2();
            o1.setId(99l);
            o1.setMeno("personToDetach");
//Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké id ale odlišný meno
            Osoba3cvUl2 o2 = new Osoba3cvUl2();
            o2.setId(99l);
            o2.setMeno("SeconDpersonToDetach");
//pre obe inštancie zavolajte em.persist
            em.persist(o1);
            em.persist(o2);
//pre druhú inštanciu zavolajte em.detach
            em.detach(o2);
//ukončite transakciu
            try {
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
//Otázka: čo spraví program po spustení? Spustite program a overte si to v DB. 
//vlozi len objekt ktori nebol detach
        } catch (Exception e) {
        }
    }
    
    void repeatFind() {
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//pomocou em.find načítajte z DB inštanciu osoby podľa zadaného klúča.
        Osoba3cvUl2 o1 = null, o2 = null;
        try {
            o1 = em.find(Osoba3cvUl2.class, 99l);
//zopakujte volanie em.find s tým istým klúčom.
            o2 = em.find(Osoba3cvUl2.class, 99l);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//Overte si či tieto dve volania vrátili ten istý objekt alebo dva rôzne objekty.
//(Porovnávajte referencie nie klúče) 
            if (o1 != null && o2 != null) {
                System.out.println((o1 == o2) ? "objects are equals" : "bjects ");
//                objecty su rovnake
            }
        }
    }
    
    void findAndDetach(){
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
        Osoba3cvUl2 o1 = null, o2 = null;
        try {
//pomocou em.find načítajte z DB inštanciu osoby podľa zadaného klúča.
            o1 = em.find(Osoba3cvUl2.class, 99l);
//zavolajte em.detach na načítanú inšanciu osoby.
            em.detach(o1);
//zopakujte volanie em.find s tým istým klúčom.
            o2 = em.find(Osoba3cvUl2.class, 99l);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//Overte si či tieto dve volania vrátili ten istý objekt alebo dva rôzne objekty. 
            if (o1 != null && o2 != null) {
                System.out.println((o1 == o2) ? "objects are equals at find&detax" : "objects  are different find&detax");
            }//objekty su rozne
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cv3C_ul2_entity_manager_methods cv = new Cv3C_ul2_entity_manager_methods();
        cv.repeatCallPersist();
        cv.repeatCallPersistNoAuto();
        cv.persistAndMerge();
        cv.functionWithClear();
        cv.functionWithDetach();
        try {
            cv.repeatFind();
            cv.findAndDetach();
        } catch (Exception e) {
            System.out.println("handeld exception out of function");
        }
    }

  
}
