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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author vsa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "autor.findByMeno", query = "SELECT a FROM autor a WHERE a.meno = :autorName"),
    @NamedQuery(name = "autor.findAll", query = "SELECT a FROM autor a"),
    })
public class autor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String meno;
    @ManyToMany//(cascade=CascadeType.PERSIST)
    private List <Kniha> knihy;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Kniha> getKnihy() {
        return knihy;
    }

    public void setKnihy(List<Kniha> knihy) {
        this.knihy = knihy;
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
        if (!(object instanceof autor)) {
            return false;
        }
        autor other = (autor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.autor_cv4u4[ id=" + id + " ]";
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }
    
}
