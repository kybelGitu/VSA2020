/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclientafterskuska;

/**
 *
 * @author Peter
 */
public class RestClientAfterSkuska {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewJerseyClient client = new NewJerseyClient();
        Skuska d = new Skuska();
        d.setPredmet("PP1");
        d.setDen("sobota");
        Skuska s = client.getXml2(Skuska.class, "MAT");
//        d.getStudents().add("Stano1");
//        d.getStudents().add("Stano2");
//        d.getStudents().add("Stano3");

        String r = client.postXml(d);

    }
    
}
