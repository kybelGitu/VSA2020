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
    private int depth;
    private String inElement;
    private String attribute;
    private String nazovPredmetu;
    private String meno_current;
    private boolean allow_reading;
    private boolean good_year;
    boolean test_name;

    public SaxHandler(String meno) {
        this.meno = meno;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        inElement = localName;
        depth++;
        if(localName == "nazov" && depth == 4 && good_year){
            allow_reading = true;
        }
        else if(localName == "predmet"){
            attribute = attributes.getValue("rocnik");
            if(attribute != null &&attribute.equals("Bc-3")){
                good_year = true;
            }
        }
//        else if(good_year && inElement == "meno"){
//            test_name = true;
//            
//        }
    }

    @Override
    public void startDocument() throws SAXException {
              depth = 0;//current depth start before enter in first tag
              inElement ="";
              attribute= "";
              nazovPredmetu = "";
              meno_current= "";
              allow_reading = false;
              good_year = false;
              test_name = false;
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        allow_reading = false;
        depth--;
        if(inElement == "predmet" && good_year){
            good_year = false;
        }
        if(test_name){
            test_name = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String current = new String(ch,start,length);
        if(depth == 4 && inElement == "nazov"){
            nazovPredmetu = current;
        }
        if(depth == 5 && inElement == "meno"&& good_year ){
            if(current.equals(meno)){
                System.out.println(nazovPredmetu);
            }
        }
        
    }

}
