/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Peter
 */
@Entity
public class KNIHA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String nazov;
    private String autor;
    private int pocet;
    

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nazov != null ? nazov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the nazov fields are not set
        if (!(object instanceof KNIHA)) {
            return false;
        }
        KNIHA other = (KNIHA) object;
        if ((this.nazov == null && other.nazov != null) || (this.nazov != null && !this.nazov.equals(other.nazov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NewEntity[ id=" + nazov + " ]";
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }
    
}
