/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author igor
 */
public class SaxHandler extends DefaultHandler {

    private String meno;
    int depth;
    boolean good_year;
    boolean allow_reading;
    boolean test_meno;
    String nazov_predmetu;
    

    public SaxHandler(String meno) {
        this.meno = meno;
        
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        depth++;
        if(good_year){// read data only if predmet contains correct year

            if ((depth == 4 && localName == "nazov")) {
                allow_reading = true;   
            }
            else if(depth == 5 && localName == "meno"){
                allow_reading = true;
                test_meno = true;
                
            }
        }
        if(depth == 3){ 
            if(qName == "predmet" && attributes.getValue("rocnik")!= null && attributes.getValue("rocnik").equals("Bc-3")){
                good_year = true;
            }
        }
        
    }
         
        

    @Override
    public void startDocument() throws SAXException {
             depth = 0;
             good_year = false;
             allow_reading = false;
             nazov_predmetu = "";
             test_meno = false;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(test_meno){
            test_meno = false;
            allow_reading = false;
        }
        else if(allow_reading && depth == 4 ){
            allow_reading = false;
        }
        else if(good_year && depth == 3 && localName.equals("predmet")){
            good_year = false;
        }
        depth--;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(depth == 5 && test_meno == true){
            String currentName = new String(ch, start, length);
            if((meno != null || currentName != null)&&currentName.equals(meno)){
                System.out.println(nazov_predmetu);
            }
            
        }
        else if(depth == 4 && allow_reading){
            nazov_predmetu = new String(ch, start, length);
        }
    }

}
