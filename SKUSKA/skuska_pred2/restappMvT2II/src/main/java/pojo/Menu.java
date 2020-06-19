/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Menu {

    public Menu() {
        jedlo  = new ArrayList<>();
    }
    
    private List<Jedlo> jedlo;

    public List<Jedlo> getJedlo() {
        return jedlo;
    }

    public void setJedlo(List<Jedlo> jedlo) {
        this.jedlo = jedlo;
    }
    
}
