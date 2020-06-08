/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3_a_vsa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vsa
 */
@Entity
@Table(name = "KNIHA")
@NamedQueries({
    @NamedQuery(name = "Kniha.findAll", query = "SELECT k FROM Kniha k"),
    @NamedQuery(name = "Kniha.findByIsbn", query = "SELECT k FROM Kniha k WHERE k.isbn = :isbn"),
    @NamedQuery(name = "Kniha.findByCena", query = "SELECT k FROM Kniha k WHERE k.cena = :cena"),
    @NamedQuery(name = "Kniha.findByNazov", query = "SELECT k FROM Kniha k WHERE k.nazov = :nazov")})
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ISBN")
    private String isbn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CENA")
    private Double cena;
    @Column(name = "NAZOV")
    private String nazov;

    public Kniha() {
    }

    public Kniha(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kniha)) {
            return false;
        }
        Kniha other = (Kniha) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkg3_a_vsa.Kniha[ isbn=" + isbn + " ]";
    }
    
}
