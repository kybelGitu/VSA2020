/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk_cv10_ul34_client;

import data.*;

/**
 *
 * @author Peter
 */
public class Sk_cv10_ul34_client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          NewJerseyClient client = new NewJerseyClient();
          
          String den = "Pia";
          String index = "99";
          
         Jedlo_client response = (Jedlo_client) client.getJedloChoice(Jedlo_client.class, den, index);
         System.out.println("jedlo na vymaz " + response.toString());
         // do whatever with response
         client.deleteJedlo(den, index);
         try {
             response = (Jedlo_client) client.getJedloChoice(Jedlo_client.class, den, index);
             System.out.println("zradlo stale existuje " + response.toString());
            
        } catch (Exception e) {
             System.out.println("nastala chyba : " + e.toString());
        }
         client.close();
    }
    
}
