/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@Entity
@Table(name = "OSOBA_3CV_UL2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Osoba3cvUl2.findAll", query = "SELECT o FROM Osoba3cvUl2 o"),
    @NamedQuery(name = "Osoba3cvUl2.findById", query = "SELECT o FROM Osoba3cvUl2 o WHERE o.id = :id"),
    @NamedQuery(name = "Osoba3cvUl2.findByNarodena", query = "SELECT o FROM Osoba3cvUl2 o WHERE o.narodena = :narodena"),
    @NamedQuery(name = "Osoba3cvUl2.findByMeno", query = "SELECT o FROM Osoba3cvUl2 o WHERE o.meno = :meno"),
    @NamedQuery(name = "Osoba3cvUl2.findByVaha", query = "SELECT o FROM Osoba3cvUl2 o WHERE o.vaha = :vaha")})
public class Osoba3cvUl2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //    uloha2 - repeatCallPersistNoAuto, bez gen. kluca  
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NARODENA")
    @Temporal(TemporalType.DATE)
    private Date narodena;
    @Column(name = "MENO")
    private String meno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VAHA")
    private Double vaha;

    public Osoba3cvUl2() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNarodena() {
        return narodena;
    }

    public void setNarodena(Date narodena) {
        this.narodena = narodena;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public Double getVaha() {
        return vaha;
    }

    public void setVaha(Double vaha) {
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
        if (!(object instanceof Osoba3cvUl2)) {
            return false;
        }
        Osoba3cvUl2 other = (Osoba3cvUl2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Osoba3cvUl2{" + "id=" + id + ", narodena=" + narodena + ", meno=" + meno + ", vaha=" + vaha + '}';
    }
   
    
}
