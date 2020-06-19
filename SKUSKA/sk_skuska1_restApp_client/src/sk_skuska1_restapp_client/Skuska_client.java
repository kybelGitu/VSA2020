/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk_skuska1_restapp_client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Peter
 */
@XmlRootElement(name = "skuska")
public class Skuska_client {

    private String predmet;
    private String den;
    private List<String> students;

    public Skuska_client() {
        this.students = new ArrayList<>();
    }
    
    

    public Skuska_client(String predmet, String den) {
        this.predmet = predmet;
        this.den = den;
        this.students = new ArrayList<>();
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
    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
    
}
