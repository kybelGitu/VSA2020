/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax_priprava1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * handler bude vypisavat kde je muka
 * @author vsa
 */
class MyHandler extends DefaultHandler {
    
        private  List <String> ReceptsWithFood;
        int depth;
        String recept_nazov;
        String ingredijencija;
        String name_of_element;
        boolean reading_allowed = false;
        String in_element;
        

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void startDocument() throws SAXException {
        //initualize 
        ReceptsWithFood = new ArrayList<String>();
        depth = 0;
        in_element = "";
    }
        
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        name_of_element = new String(ch, start, length);
       if(depth == 3 && reading_allowed ){//je v nezve receptu
             recept_nazov = name_of_element;
       }
       else if(depth == 4 && name_of_element.equals("muka")){
           
           System.out.println( recept_nazov + " obsahuje muku ");
       }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        reading_allowed = false;
        in_element = "";
        depth--;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        in_element = localName;
        depth++;
        if(localName == "nazov"|| localName == "mnozstvo" ){
            reading_allowed = true;
        }
        
    }
    
    

    public MyHandler() {

    }
    
    
    
}
