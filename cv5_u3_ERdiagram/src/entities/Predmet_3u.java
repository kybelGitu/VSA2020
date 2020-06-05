/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author vsa
 */
@Entity
public class Predmet_3u implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    private int kredit;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Osoba_3u prednasajuci;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List <Osoba_3u> cviciaci;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Predmet_3u)) {
            return false;
        }
        Predmet_3u other = (Predmet_3u) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Predmet[ id=" + id + " ]";
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public Osoba_3u getPrednasajuci() {
        return prednasajuci;
    }

    public void setPrednasajuci(Osoba_3u prednasajuci) {
        this.prednasajuci = prednasajuci;
    }

    public List <Osoba_3u> getCviciaci() {
        return cviciaci;
    }

    public void setCviciaci(List <Osoba_3u> cviciaci) {
        this.cviciaci = cviciaci;
    }
    
}
