/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk_skuska1_restapp_client;


/**
 *
 * @author Peter
 */
public class Sk_skuska1_restApp_client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewJerseyClient client = new NewJerseyClient();
        String predmet = "MAT";
        Skuska_client sk =  client.getSkuska(Skuska_client.class, predmet);
        System.out.println(sk.getDen());
        String Student = "cigo";
        client.addStudent(Student, predmet);
        Long num = Long.parseLong(client.getSkCount(predmet));
        System.out.println(num);
        Skuska_client sk1 = new Skuska_client("fyz", "utorok");
        Skuska_client sk2 = new Skuska_client("fyz", "streda");
        String res = client.newSkuska(sk1);
        res += client.newSkuska(sk2);
        System.out.println( "result> " + res);
    }
    
}
