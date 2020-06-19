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
//   @XmlRootElement(name='skuska')
@XmlRootElement(name="skuska")
public class Skuska {
    private String predmet;
    private String den;
    private List <String> students;
    
    Skuska(){
        this.students = new ArrayList<String>();
        
    }
    
//    @XmlElement
    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

//    @XmlElement
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

//    @XmlElement
    public List <String> getStudents() {
        return students;
    }

    public void setStudents(List <String> students) {
        this.students = students;
    }
}
