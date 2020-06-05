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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
public class Vydavatelstvo_cv4_ul2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String adresa;
    @OneToMany
    @JoinColumn(name = "vydavatel_fk")
    private List<Kniha_cv4_ul2> publikacia;

    /**
     * Get the value of adresa
     *
     * @return the value of adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Set the value of adresa
     *
     * @param adresa new value of adresa
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
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
        if (!(object instanceof Vydavatelstvo_cv4_ul2)) {
            return false;
        }
        Vydavatelstvo_cv4_ul2 other = (Vydavatelstvo_cv4_ul2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Vydavatelstvo_cv4_ul2[ id=" + id + " ]";
    }

    public List<Kniha_cv4_ul2> getPublikacia() {
        return publikacia;
    }

    public void setPublikacia(List<Kniha_cv4_ul2> publikacia) {
        this.publikacia = publikacia;
    }
    
}
