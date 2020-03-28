/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vsa
 * pozrite si vygenerovaný zdrojový kód triedy Osoba. Všimnite si,
 * že trieda má zatiaľ jedinú property id, ktorá bude primárnym autogenerovaným klúčom odpovedajúcej DB tabuľky. 
 */

@Entity
@Table(name = "T_OSOBA")
//ULOHA 3-2
//V triede Osoba k anotácii @Entity pridajte ešte anotáciu @Table(name = "T_OSOBA")
//Spustite program, mal by vytvoriť tabuľku T_OSOBA s rovnakým obsahom ako OSOBA. 

public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
//    pozrite si vygenerovaný zdrojový kód triedy Osoba. Všimnite si, že trieda má zatiaľ jedinú property id,
//    ktorá bude primárnym autogenerovaným klúčom odpovedajúcej DB tabuľky. 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*pridajte do triedy nasledujúce property:

    meno :String
    narodena :Date
    vaha :Float */
    private Long id;
    private String meno;
    @Temporal(TemporalType.DATE)
    private Date narodena;
//    k property vaha pridajte anotáciu s @Column(nullable=false)
//Spustite program. Pravdepodobne skončí chybou. Presvedče sa o tom v logu.
    //float: Exception in thread "main" java.lang.ExceptionInInitializerError
    //Float: Exception in thread "main" Local Exception Stack:, java.lang.NullPointerException
//
//Pri vytváraní osôb v programe inicializujte ich váhu. (Doteraz mohla byť null pretože typ property vaha je Float a nie float)
//Spustite program. Mal by bežať bez chýb.
//
//Presvedče sa v DB, stĺpec VAHA je notNull (Narozdiel od stĺpca VAHA v tabuľke OSOBA z Ulohy2).
//    @Column(nullable=false)
    private Float vaha = 1f;//prgram inicializuje vahu Float

    /**
     *
     * @return id osoby
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param meno
     */
    public void setMeno(String meno) {
        this.meno = meno;
    }

    /**
     *
     * @param narodena
     */
    public void setNarodena(Date narodena) {
        this.narodena = narodena;
    }

    /**
     *
     * @param vaha
     */
    public void setVaha(float vaha) {
        this.vaha = vaha;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Osoba)) {
            return false;
        }
        Osoba other = (Osoba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Osoba[ id=" + id + " ]";
    }
    
}
