/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Peter
 */
@XmlRootElement(name = "skuska")
public class Skuska {

    private String predmet;
    private String den;
    private List<String> student;

    public Skuska() {
        this.student = new ArrayList<>();
    }
    
    

    public Skuska(String predmet, String den) {
        this.predmet = predmet;
        this.den = den;
        this.student = new ArrayList<>();
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }
    
    @XmlElement(name = "student")
    public List<String> getStudent() {
        return student;
    }

    public void setStudent(List<String> student) {
        this.student = student;
    }
    
}
