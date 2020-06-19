/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="skuska")
public class Data {
    
    private String predmet;

    private String den;
    
    private List<String> student;

    public List<String> getStudent() {
        return student;
    }

    public Data() {
        student = new ArrayList<>();
        
    }

    public void setStudent(List<String> student) {
        this.student = student;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

}
