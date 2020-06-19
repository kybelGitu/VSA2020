/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Peter
 */

@XmlRootElement(name="menu")
public class DayObj {
    private String den;
    // only for server
    private Map <Integer, Jedlo> jedla;
//    to return to client (API CANNOT RETURN MAP)
    private List <Jedlo> jedalnyList_ok;

    public DayObj() {
        this.jedla = new TreeMap<>();
//        jedalnyList_ok = new ArrayList<>();
        
    }

    
    @XmlElement(name="den")
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }
    
    @XmlTransient
    public Map <Integer, Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(Map <Integer, Jedlo> jedla) {
        this.jedla = jedla;
    }
    
    @XmlElementWrapper(name = "PonukaDna")
    @XmlElement(name = "zradlo")
    public List <Jedlo> getJedalnyList_ok() {
        this.jedalnyList_ok = new ArrayList(jedla.values());
        return jedalnyList_ok;
    }

    public void setJedalnyList_ok(List <Jedlo> jedalnyList_ok) {
        this.jedalnyList_ok = jedalnyList_ok;
    }
    
}
