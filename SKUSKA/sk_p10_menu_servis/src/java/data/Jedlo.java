/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="jedlo")
public class Jedlo {
    
    private String nazov;

    private double cena;

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String toString() {
        return "Jedlo{" + "nazov=" + nazov + ", cena=" + cena + '}';
    }

        public Jedlo() {
    }
        
    public Jedlo(String nazov, double cena) {
        this.nazov = nazov;
        this.cena = cena;
    }
}