/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv1.pkg11._sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author vsa
 */
public class Cv111_SAX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
/*        Implementujte program, ktorý s využitím SAX parsera číta XML súbor s kuchárskymi receptami a vypíše názvy všetkých receptov, ktoré obsahujú múku. */
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);    // dolezite aby extrahoval localName

        SAXParser saxParser = spf.newSAXParser();
        MyHandler h = new MyHandler();
        
        saxParser.parse("/home/vsa/Desktop/WORK/cv1(11)_SAX/src/cv1/pkg11/_sax/receptar.xml",h);
        h.vypisRecepty();
        // save names of recepts that contains food to the Map with key name and list of recepts of the food
        Map <String, List <String> > recepty_S_jedlom = new HashMap<String, List<String>>();
        for(Object O : args){
            //inputs.add((String) O);
            String currentFood = (String) O;
            List <String> currRecepts  = h.vypisRecepty(currentFood);
            //save current recepts to Map 
            recepty_S_jedlom.put(currentFood, currRecepts);
        }
        //vypis recepty obsahujuce potraviny
        Iterator itr = recepty_S_jedlom.entrySet().iterator(); 
        System.out.println("Recepty obsahujuce polozky: ");
        while(itr.hasNext()) 
        { 
             Map.Entry mapElement = (Map.Entry)itr.next(); 
             System.out.println("polozka: " + mapElement.getKey() +  
                                 "--> RECEPTY  " + mapElement.getValue()); 
        }
//        Implementujte program, ktorý s využitím SAX parsera číta XML súbor s kuchárskymi receptami a zistí aké množstvo mlieka ide do omelety;
//          na obrazovku vypíše množstvo aj jednotku. 
       Pair<String, String> pair = new Pair<>("omelseta", "mlieko");
       Pair<Double , String> p = h.obsahuje(pair);
       if(p.getKey().equals(Double.NaN)){//Problem?!
           System.out.println("jedlo " + pair.getKey() + " obsahuje " + p.getKey() + p.getValue());
       }
       else {
           System.out.println(p.getValue());
       }
        

    }
    
}
