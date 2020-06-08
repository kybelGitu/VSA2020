/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p9restapp1client;

import proxy.NewJerseyClient;

/**
 *
 * @author igor
 */
public class P9restapp1client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient client = new NewJerseyClient();
        
        client.pridajJedlo("Gulas");
        client.pridajJedlo("Rezen");
        String pocet = client.getPocet();
        System.out.println("  pocet jedal: " + pocet);

        int n = Integer.parseInt(pocet);
        for (int i=0; i<n; i++) {
            System.out.println(client.getJedlo("" + i));
        }
        System.out.println("  odstranim jedlo 0...");
        client.odstranJedlo("0");
        System.out.println(client.getJedlo("0"));


    }
    
}
