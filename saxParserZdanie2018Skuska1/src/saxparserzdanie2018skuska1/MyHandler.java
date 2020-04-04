/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparserzdanie2018skuska1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



/**
 *
 * @author vsa
 */
public class MyHandler extends DefaultHandler{
    //depths values const
    private static final int KNIHKUPECTVO_NAZOV = 2;
    private static final int KNIHA = 3;
    private static final int KNIHA_ELEMENTS = 3;
    
    
    int depth;
    boolean reading_allowed;
    Double maxCena;
    String attribute;
    String inElement;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        depth--;
        reading_allowed = false;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        depth++;
        if(localName == "meno" || localName == "cena" || localName == "autor"){
            reading_allowed = true;
        }
        else if (localName == "kniha"){
            attribute = attributes.getValue(qName);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startDocument() throws SAXException {
//        INITIEALIZE VALUES WHEN PARESER BEGIN READS THE DOCUMENT
        depth = 0;
        reading_allowed = false;
        maxCena = Double.MIN_VALUE;
        
    }
    
    
    
}
