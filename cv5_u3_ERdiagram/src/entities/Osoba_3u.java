/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
public class Osoba_3u implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String meno;
    @OneToMany(mappedBy = "prednasajuci")
    private List <Predmet_3u>  prednasane_predmety;
    @ManyToMany(mappedBy = "cviciaci")
    private List <Predmet_3u> cvicene_predmety;

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
        if (!(object instanceof Osoba_3u)) {
            return false;
        }
        Osoba_3u other = (Osoba_3u) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Osoba_3u[ id=" + id + " ]";
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public List <Predmet_3u> getPrednasane_predmety() {
        return prednasane_predmety;
    }

    public void setPrednasane_predmety(List <Predmet_3u> prednasane_predmety) {
        this.prednasane_predmety = prednasane_predmety;
    }

    public List <Predmet_3u> getCvicene_predmety() {
        return cvicene_predmety;
    }

    public void setCvicene_predmety(List <Predmet_3u> cvicene_predmety) {
        this.cvicene_predmety = cvicene_predmety;
    }
    
}
