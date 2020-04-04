/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax_priprava1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *<ol>
 * <li>
 * implementujte program, ktorý s využitím SAX parsera číta XML súbor s kuchárskymi receptami a vypíše názvy všetkých receptov, ktoré obsahujú múku. 
 * </li>
 *</ol>
 *
 * @author vsa
 */
public class Sax_priprava1 {
    private static SAXParser  saxParser;

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        Sax_priprava1 cv = new Sax_priprava1();
        cv.processData("/home/vsa/Desktop/WORK/sax_priprava1_uloha3/src/xmlData/receptar.xml");
        
        
    }
    //constructor
    //initialize parser

    /**
     *
     */
    public Sax_priprava1() {
        
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);    // dolezite aby extrahoval localName
            saxParser = spf.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // function to utilize parser

    /**
     *
     * @param filePath
     */
    public void processData(String filePath){
        try {
            saxParser.parse(filePath, new MyHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    //use before inserting url to file url to parser.parse()
    //https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
        
}
