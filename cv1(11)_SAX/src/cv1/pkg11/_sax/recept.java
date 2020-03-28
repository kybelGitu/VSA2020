/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv1.pkg11._sax;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author vsa
 */
 public class recept {
    private String nazov;
    Set<polozka> polozky;
    
    void vypisPolozky(){
        System.out.println("nazov receptu: " + this.nazov + " polozky: " );
        if(this.polozky!=null){
            for(Object o : this.polozky){
                polozka current = (polozka) o;
                System.out.println("POLOZKA: " + current.nazov + " mnozstvo: " + current.mnozstvo + current.jednotka );
            }
        }
            

    }
    
    /**
     *
     */
    public  recept(){
        this.nazov = new String();
        this.polozky = new HashSet<polozka>();
    }

    /**
     *
     * @param nazov
     */
    public void setNazov(String nazov){
        this.nazov = nazov;
    }

    /**
     *
     * @param P
     */
    public void pridajPolozku(polozka P){
        this.polozky.add(P);
    }

    /**
     *
     * @return
     */
    public String getNazov(){
        return this.nazov;
    }
       
}

class polozka {
    String nazov;
    double mnozstvo;
    String jednotka;
}

