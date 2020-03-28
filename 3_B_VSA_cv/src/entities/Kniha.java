/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
@Entity
public class Kniha implements Serializable {
    
     EntityManagerFactory emf;
    EntityManager em;

    private static final long serialVersionUID = 1L;
    @Id
    private String isbn;
    private String nazov;
    private double cena;
//    @GeneratedValue(strategy = GenerationType.AUTO)
    

    public String getIsbn() {
        return isbn;
    }

    public String getNazov() {
        return nazov;
    }

    public double getCena() {
        return cena;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Kniha{" + "isbn=" + isbn + ", nazov=" + nazov + ", cena=" + cena + '}';
    }
    
    
}
