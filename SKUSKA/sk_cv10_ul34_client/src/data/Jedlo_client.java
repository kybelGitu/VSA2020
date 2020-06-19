/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Peter
 */
@XmlRootElement(name="zradlo")
//@XmlRootElement
public class Jedlo_client {
    private String name;
    private double cena;

    public Jedlo_client() {
    }

    public Jedlo_client(String name, double cena) {
        this.name = name;
        this.cena = cena;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Jedlo{" + "name=" + name + ", cena=" + cena + '}';
    }
    
    
}
