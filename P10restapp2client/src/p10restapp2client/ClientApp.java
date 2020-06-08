/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p10restapp2client;

import p10restapp2client.Jedlo;
import proxy.NewJerseyClient;

/**
 *
 * @author igor
 */
public class ClientApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient client = new NewJerseyClient();
        System.out.println(client.getPocet());
        Jedlo j0 = client.getJedlo(Jedlo.class, "0");
        System.out.println(j0.getNazov() + " : " + j0.getCena());
    }
    
}
