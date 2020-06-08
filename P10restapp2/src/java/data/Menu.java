/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement
public class Menu {
    private List<Jedlo> jedla;

    public List<Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(List<Jedlo> jedla) {
        this.jedla = jedla;
    }

    public Menu() {
        jedla = new ArrayList<>();
    }
    
}
