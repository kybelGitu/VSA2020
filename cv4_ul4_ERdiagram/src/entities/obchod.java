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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "obchod.findByNazov", query = "SELECT o FROM obchod o WHERE o.nazov = :nazovObchodu"),
    })
public class obchod implements Serializable {

    private String nazov;
    @OneToMany( mappedBy="obchod_at_polozka")//cascade=CascadeType.PERSIST,olphran remove
    private List <polozka> polozky;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof obchod)) {
            return false;
        }
        obchod other = (obchod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "obchod{" + "nazov=" + nazov + ", polozky=" + polozky.toString() + ", id=" + id + '}';
    }

    
}
