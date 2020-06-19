/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Peter
 */

@XmlRootElement
public class skusky {
    
    private List<Skuska> skusky;

    public List<Skuska> getSkusky() {
        return skusky;
    }

    public void setSkusky(List<Skuska> skusky) {
        this.skusky = skusky;
    }

    public skusky(List<Skuska> skusky) {
        this.skusky = new ArrayList<Skuska>();
    }
    
    
    
}
