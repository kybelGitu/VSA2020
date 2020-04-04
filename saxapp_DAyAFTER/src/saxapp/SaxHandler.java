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
 * @author peter
 * 
 * firstly i need to find attribute for comparing study years
 * then i'll find other params but a i can find params in
 * different orders
 * because of that i need at function characters test wich paramaters i hava
 */
public class SaxHandler extends DefaultHandler {

    private String meno;
    private String predmet;
    private String currentMeno;
    /* i use 2 booleans for coding for all opeartions states
           code          operation
            00    search attribute rocnik
            11    correct year
            10    found nazov predmetu -< predmet
            01    found meno prednasajuci
    
    */
    private boolean bool1;
    private boolean bool2;

    /**
     *
     * @param meno
     */
    public SaxHandler(String meno) {
        this.meno = meno;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //check if is obtained element
        if(bool1 ^ bool2){
            String currentText = new String(ch,start, length);
            // TO TESTING DUPLICATE ELEMENTS, TEST IF SAVED PARAMETERS
            // ARE NULL (predmet, currentMeno == null
            //case (1, 0) obtained nazov predmetu
            if(bool1 && !bool2){
                predmet = currentText;
            }//case (0, 1) obtained meno prednasajuci
            else//only 2 cases possible
                currentMeno = currentText;
        }
        else 
            return;
        
        //check if all two params all obtained
        if (predmet != null && currentMeno != null) {
            if (currentMeno.equals(meno)) {
                System.out.println(predmet);
            }
        }                
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if(bool1 ^ bool2){//end of founded element
                if(bool1){//predmet
                    bool2 = true;
                }
                else//meno
                    bool1 = true;
            }
            else if(bool1 && bool2) {//other elements
                if(localName.equals("predmet")){//end of predmet reset class values
                    reset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }



    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startDocument() throws SAXException {
        reset();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            //correcting year
            if(localName.equals("predmet")){
                if(attributes.getValue("rocnik").equals("Bc-1")){
                    bool1 = true;
                    bool2 = true;
                }
            }
            //correct year && correct one from next two params
            else if( bool1 &&  bool2 ){
                if(localName.equals("nazov")){
                    bool2 = false;
                }
                else if(localName.equals("meno")){
                    bool1 = false;
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }
    
    /**
     * reset to default states
     */
    public void reset(){
        bool1 = false;
        bool2 = false;
        predmet = null;
        currentMeno = null;
    }
    

    

}
