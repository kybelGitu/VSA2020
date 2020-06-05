/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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
public class Kniha_cv4_ul2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    @ManyToMany(cascade=CascadeType.PERSIST)
    @ElementCollection
    private List<Osoba_cv4_ul2> autor;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Vydavatelstvo_cv4_ul2 vydavatel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of vydavatel
     *
     * @return the value of vydavatel
     */
    public Vydavatelstvo_cv4_ul2 getVydavatel() {
        return vydavatel;
    }

    /**
     * Set the value of vydavatel
     *
     * @param vydavatel new value of vydavatel
     */
    public void setVydavatel(Vydavatelstvo_cv4_ul2 vydavatel) {
        this.vydavatel = vydavatel;
    }

    /**
     * Get the value of nazov
     *
     * @return the value of nazov
     */
    public String getNazov() {
        return nazov;
    }

    /**
     * Set the value of nazov
     *
     * @param nazov new value of nazov
     */
    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Get the value of autor
     *
     * @return the value of autor
     */
    public List<Osoba_cv4_ul2> getAutor() {
        return autor;
    }

    /**
     * Set the value of autor
     *
     * @param autor new value of autor
     */
    public void setAutor(List<Osoba_cv4_ul2> autor) {
        this.autor = autor;
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
        if (!(object instanceof Kniha_cv4_ul2)) {
            return false;
        }
        Kniha_cv4_ul2 other = (Kniha_cv4_ul2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.kniha_cv4_ul2[ id=" + id + " ]";
    }
    
}
