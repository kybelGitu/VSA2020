/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapocet2;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "Predmet")
@NamedQueries({
    @NamedQuery(name = "Predmet.findByKod", query = "SELECT p FROM Predmet p WHERE p.kod = :kod"),
})
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Column(unique=true)
    private String kod;                     // primarny kluc - kod predmetu
    private String rocnik;
    @ManyToMany(mappedBy = "predmety")
    private Set<Osoba> vyucujuci;           // osoby, ktore ucia predmet


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kod != null ? kod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the kod fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Predmet[ kod=" + kod + " ]";
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getRocnik() {
        return rocnik;
    }

    public void setRocnik(String rocnik) {
        this.rocnik = rocnik;
    }

    public Set<Osoba> getVyucujuci() {
        return vyucujuci;
    }

    public void setVyucujuci(Set<Osoba> vyucujuci) {
        this.vyucujuci = vyucujuci;
    }

}
