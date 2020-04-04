/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author igor
 */
public class SaxHandlerTest {
    
    public SaxHandlerTest() {
    }

    @Test
    public void ut_01() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_1.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA") && !vystup.contains("null") && !vystup.contains("XY")); 
    }
    
    @Test
    public void ut_02() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_2.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY")); 
    }
    
    @Test
    public void ut_03() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_3.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY")); 
    }
    
    @Test
    public void ut_04() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_4.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY")  && vystup.contains("OOP")); 
    }
    
    @Test
    public void ut_05() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_5.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY")); 
    }
    
    @Test
    public void ut_06() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_6.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY")); 
    }
    
    @Test
    public void ut_07() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_7.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY")); 
    }
    
    @Test
    public void ut_08() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_8.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY")); 
    }
    
    @Test
    public void ut_09() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_9.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY") && !vystup.contains("Mrk")); 
    }
    
    @Test
    public void ut_10() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_10.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY") && !vystup.contains("Mrk")); 
    }
    
    @Test
    public void ut_11() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_11.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY") && !vystup.contains("Mrk")); 
    }
    
    @Test
    public void ut_12() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_12.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY") && !vystup.contains("Mrk")); 
    }
    
    @Test
    public void ut_13() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_13.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), !vystup.contains("VSA")  && !vystup.contains("null") && !vystup.contains("XY") && !vystup.contains("Mrk")); 
    }
    
    @Test
    public void ut_14() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/data_14.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("VSA") && !vystup.contains("null") && !vystup.contains("XY") && !vystup.contains("Mrk")); 
    }
        
    @Test
    public void ut99() throws Exception {
        // volanie parsera
        String vystup = parsexml("src/test/data/studium.xml");
        
        // validacia odchyteneho vystupu handlera 
        assertTrue("CHYBA:" + vystup.replace('\n', ' '), vystup.contains("NeuronoveSiete")); 
    }
    
    
    
    private String parsexml(String xml) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);    
        SAXParser saxParser = spf.newSAXParser();
        
        // presmerovanie standardneho vystupu do ByteArray aby sme odchytili co do neho pise handler
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        // junit zrejme uz ma standardny vystup kdesi presmerovany a preto si to treba odlozit
        PrintStream origOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        // volanie parsera
        saxParser.parse(xml, new SaxHandler("Mrkvicka"));
        
        // presmerovanie standardneho vystupu naspat
        System.setOut(origOut);
        
        // vypis odchyteneho vystupu handlera 
//        System.out.println("---parsing " + xml);
//        System.out.print(outContent.toString());
        return outContent.toString().trim();     
    }
    
}
