/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
public class PredmetCV1B implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    private int kredity = 0;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "garant_id")
    private OsobaCV1B garant;
    

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
        if (!(object instanceof PredmetCV1B)) {
            return false;
        }
        PredmetCV1B other = (PredmetCV1B) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PredmetCV1B[ id=" + id + " ]";
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getKredity() {
        return kredity;
    }

    public void setKredity(int kredity) {
        this.kredity = kredity;
    }

    public OsobaCV1B getGarant() {
        return garant;
    }

    public void setGarant(OsobaCV1B garant) {
        this.garant = garant;
    }
    
}
