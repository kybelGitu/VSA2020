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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Kniha.findByNazov", query = "SELECT k FROM Kniha k WHERE k.nazov = :nazovKnihy"),
    })
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    private String nazov;
//    @ManyToMany(cascade=CascadeType.PERSIST)
//    private List <autor> autors;
    @ManyToOne//(cascade=CascadeType.PERSIST) vyhotovitel moze existovat aj bez knihy skor olphran delete jedine asi
    private vydavatel vydavatel;
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="kniha_at_polozka")
    private List <polozka> polozky;

    public vydavatel getVydavatel() {
        return vydavatel;
    }

    public void setVydavatel(vydavatel vydavatel) {
        this.vydavatel = vydavatel;
    }

    public List<polozka> getPolozky() {
        return polozky;
    }

    public void setPolozky(List<polozka> polozky) {
        this.polozky = polozky;
    }

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
        if (!(object instanceof Kniha)) {
            return false;
        }
        Kniha other = (Kniha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Kniha[ id=" + id + " ]";
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
    
}
