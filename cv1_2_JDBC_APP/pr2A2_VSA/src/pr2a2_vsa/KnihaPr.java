/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2a2_vsa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@Entity
@Table(name = "KNIHA_PR", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KnihaPr.findAll", query = "SELECT k FROM KnihaPr k"),
    @NamedQuery(name = "KnihaPr.findByNazov", query = "SELECT k FROM KnihaPr k WHERE k.nazov = :nazov"),
    @NamedQuery(name = "KnihaPr.findByCena", query = "SELECT k FROM KnihaPr k WHERE k.cena = :cena")})
public class KnihaPr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NAZOV")
    private String nazov;
    @Basic(optional = false)
    @Column(name = "CENA")
    private float cena;

    public KnihaPr() {
    }

    public KnihaPr(String nazov) {
        this.nazov = nazov;
    }

    public KnihaPr(String nazov, float cena) {
        this.nazov = nazov;
        this.cena = cena;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nazov != null ? nazov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KnihaPr)) {
            return false;
        }
        KnihaPr other = (KnihaPr) object;
        if ((this.nazov == null && other.nazov != null) || (this.nazov != null && !this.nazov.equals(other.nazov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pr2a2_vsa.KnihaPr[ nazov=" + nazov + " ]";
    }
    
}
