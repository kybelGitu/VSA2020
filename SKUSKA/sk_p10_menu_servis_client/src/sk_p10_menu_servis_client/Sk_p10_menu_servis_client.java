/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk_p10_menu_servis_client;

import java.util.Set;

/**
 *
 * @author Peter
 */
public class Sk_p10_menu_servis_client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewJerseyClient c = new NewJerseyClient();
        
        
        Long num = c.getTest(Long.class);
        System.out.println("returned number " + num);
        Jedlo_client gottedEat = c.getXml(Jedlo_client.class, "2");
        
        if(gottedEat!= null){
            System.out.println(gottedEat.toString());
        }
        else{
            System.out.println("je to null");
        }
        
        Jedlo_client j = new Jedlo_client();
        j.setCena(4.58d);
        j.setNazov("kosicke file");
        String l = c.postXml(j, String.class);
//        c.postXml(j);
        System.out.println("server was retruned>: " + l);
        
    }
    
}
