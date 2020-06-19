/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk_p10_menu_servis_client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="jedlo")
public class Jedlo_client {
    
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

        public Jedlo_client() {
    }

    public Jedlo_client(String nazov, double cena) {
        this.nazov = nazov;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Jedlo_client{" + "nazov=" + nazov + ", cena=" + cena + '}';
    }
}