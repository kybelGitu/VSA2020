/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv2_persistance_entity_class;

import entities.Osoba;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *<p>
 * 6 uloh + 1 teoreticka
 * <a href='http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV2'> <br>http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV2</a><br>
 * 0 / 6 +1 uloh
 * Vytvorenie DB tabuľky z entitnej triedy. (precvičenie učiva z prednášky)
 * <ul>
 * <li>uloha 1 - vytvorenie db z entitnej triedy</li>
 * <li><b>Úloha 2 - postupne modifukujte program z 1. úlohy a vyskúšajte si rôzne možnosti práce s kľúčmi</b>
 * <ul>
 * <li><u>Odstráňte anotáciu @GeneratedValue a spustite program znovu. Aký problém nastane? Overte si to v logu. </u><br>
 * <i>Exception vklada sa null value - zakazane</i>
 * </li>
 * <li><u>Pri vytváraní nových osôb treba zadať každej unikátne id, doplňte to a spustite znovu program. Overte si že do DB boli pridané nové záznamy s vami zadanými hodnotami id.  </u><br>
 * <i>vlozi data aj pri odkomentovanii @GeneratedValue</i>
 * </li>
 * <li><u>V persistence.xml zmeňte table generation strategy na: drop-and-create  </u><br>
 * <i>netreba stale menit uz pouzite id's</i>
 * </li>
 * <li><strong><u><i>Program môžeme teraz spušťať opakovane bez problémov. Tabuľka OSOBA zakaždým sa nanovo vytvorí a naplní, t.j. jej obsah by mal byť po každom spustení rovnaký (užitočné pre unit-testy) </i></u></strong>
 * </li>
 * </ul>
 * </li>
 * <li><b>Úloha 3 - program z predchádajúcej úlohy ďalej modifikujeme tak, aby</b></li>
 * <ol type="1"><li><p class="line891"><strong>Vytvorená tabuľka mala iné meno:</strong> <span class="anchor" id="line-38"></span></p><ul><li>V triede Osoba k anotácii @Entity pridajte ešte anotáciu @Table(name = "T_OSOBA") <span class="anchor" id="line-39"></span></li><li>Spustite program, mal by vytvoriť tabuľku T_OSOBA s rovnakým obsahom ako OSOBA. <span class="anchor" id="line-40"></span></li></ul></li><li><p class="line891"><strong>Stĺpec VAHA bol povinný:</strong> <span class="anchor" id="line-41"></span></p><ul><li><p class="line862">k property <strong>vaha</strong> pridajte anotáciu s <strong>@Column(nullable=false)</strong>  <span class="anchor" id="line-42"></span></p></li><li>Spustite program. Pravdepodobne skončí chybou. Presvedče sa o tom v logu. <span class="anchor" id="line-43"></span></li><li><p class="line862">Pri vytváraní osôb v programe inicializujte ich váhu. <em>(Doteraz mohla byť null pretože typ property vaha je Float a nie float)</em> <span class="anchor" id="line-44"></span></p></li><li>Spustite program. Mal by bežať bez chýb. <span class="anchor" id="line-45"></span></li><li><p class="line862">Presvedče sa v DB, stĺpec VAHA je <strong>notNull</strong> <em>(Narozdiel od stĺpca VAHA v tabuľke OSOBA z Ulohy2).</em> <span class="anchor" id="line-46"></span></p></li></ul></li><li><p class="line891"><strong>Tabuľka bola vytvorená v inej databáze</strong> <span class="anchor" id="line-47"></span></p><ul><li><p class="line862">vytvorte novú JavaDB databazu: V záložke <em><strong>services.Databases.JavaDB </strong></em> otvorte dialóg <em><strong>Create Database...</strong></em> a zadajte meno novej databázy (napr. vsa) a ostatné prihlasovacie údaje (rovnaké ako v databáze sample)  <span class="anchor" id="line-48"></span></p></li><li><p class="line862">v <strong>persistence.xml</strong> zadajte url novovytvorenej databázy  <span class="anchor" id="line-49"></span></p></li><li>Spustite program, mal by vytvoriť tabuľku v novovytvorenej databáze. <span class="anchor" id="line-50"></span><span class="anchor" id="line-51"></span></li></ul></li></ol>
 * 
 * 
 * </ul>
 * </p>
 * odkomentovat anotacie @table,@column a pokracovat uloha 3/3
 * @author vsa
 * 
 */
public class Cv2_1_Persistance_entity_class {

    /**
     * @param args the command line arguments

     */
    public static void main(String[] args) {
//        Implementujte program (main), ktorý vytvorí inštancie niekoľkých osôb a pomocou entity managera ich vloží do databázy
//
//    najmenej jedna osoba má zadané všetky údaje
    Osoba o = new Osoba();
    o.setId((long) 41441);
    o.setMeno("jozef arasid");
    o.setNarodena(new Date());
    o.setVaha( 88.154123f);
    persist(o); 
//    jedna osoba nemá inicializovaný žiadny údaj
    o = new Osoba();
    o.setId((long) 55);
    persist(o);
//        Pozn. id nie je potrebné zadávať, vygeneruje ho DB pri vložení nového záznamu 
//    na konci programu vypíšte vygenerované id osôb. (vypismi sa môžete presvedčiť, že id obsahuje hodnotu ža po commite) 
//      len MENO
    o = new Osoba();
    o.setId((long) 5555);
    o.setMeno("gargamel451");
    persist(o);
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv2_Persistance_entity_classPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    
}
