/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparserzdanie2018skuska1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author vsa
 */
public class SaxParserZdanie2018Skuska1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
    SaxParserZdanie2018Skuska1() throws IOException{
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);    // dolezite aby extrahoval localName

        SAXParser saxParser;
        try {
            saxParser = spf.newSAXParser();
            saxParser.parse("Data.xml", new MyHandler());
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SaxParserZdanie2018Skuska1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SaxParserZdanie2018Skuska1.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
