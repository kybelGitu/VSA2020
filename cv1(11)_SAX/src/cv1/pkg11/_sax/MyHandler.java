/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv1.pkg11._sax;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//import org.xml.sax.HandlerBase;

/**
 *
 * @author vsa
 */
class MyHandler extends DefaultHandler {
    //USED AT FUNC CHARACTERS
    String text;
    
    int depth = 0;
    List elements = new ArrayList<recept>();
    recept actRecept = null;
    polozka actPolozka = null;
    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
     public void endElement(String uri, String localName, String qName) throws SAXException {
       if(localName.equals("nazov")) {
            if(depth == 1){
                this.actRecept.setNazov(text);
                this.elements.add(this.actRecept);
                
            }
            else if(depth == 2){
                this.actPolozka.nazov = text;
                
            }
        }
        else if(localName.equals("mnozstvo")){
            this.actPolozka.mnozstvo = Double.parseDouble(text);    
        }
        else if(localName.equals("popis")){
            
        }
        else {
            if(localName.equals("polozka")){
                this.actRecept.polozky.add(this.actPolozka);
            }
            depth--;
        }

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("recept") ||localName.equals("polozka")  ) {
            if(localName.equals("polozka")){
                this.actPolozka = new polozka();
            }
            else if(localName.equals("recept")){
                //create new "empty" recept
                this.actRecept = new recept();
            }
            depth++;
        }
        else if(localName.equals("mnozstvo")){
            this.actPolozka.jednotka = attributes.getValue("jednotka");
        }
        else {
            
        }
        
    }
    public void vypisRecepty(){
        for(Object i : this.elements){
            recept current = (recept) i;
            current.vypisPolozky();
        }
    }
    
    public List<String> vypisRecepty(String food){
        List<String> output = new ArrayList<String>();
        for(Object i : this.elements){
            recept current = (recept) i;
            for(Object j : current.polozky){
                polozka POLOZKA = (polozka) j;
                if(POLOZKA.nazov.equals(food)){
                    output.add(current.getNazov());
                }
            }
        }
        return output;
    }
    
    
    public Pair<Double,String> obsahuje(Pair <String, String> input){
        Pair<Double, String> pair = new Pair<>(Double.NaN, "recept neexistuje");
        for(Object i : this.elements){
            recept current = (recept) i;
            if(input.getKey().equals(current.getNazov())){
                //recept exists
                pair = new Pair<>(Double.NaN, "recept neobsahuje polozku");//Par sa neda zmenit 
                for(Object j : current.polozky){
                    polozka POLOZKA = (polozka) j;
                    if(POLOZKA.nazov.equals(input.getValue())){
                        pair = new Pair<>(POLOZKA.mnozstvo, POLOZKA.jednotka);
                        return pair;
                    }
                }
                System.out.println("recept neobsahuje polozku");
            }
        }
//        System.out.println("recept neexistuje");                  - chyba!!!<????
       System.out.println("recept neexistuje");
        return pair;             
    }
    
   
}
