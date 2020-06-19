/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestClientTest {
    
    static private RestClient client;

    public RestClientTest() {
    }

    static private Server jettyServer;

    @BeforeClass
    static public void setUp() {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/RestUnitTest/resources");

        jettyServer = new Server(9999);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "rest");

        try {
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        client = new RestClient();
    }

    @AfterClass
    static public void tearDown() {
        try {
            jettyServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 1. po starte existuje jediny resource pre skusku z MAT v utorok.
    // 1.1  getxml(MAT)
    //      check not null
    //      check predmet = MAT
    //      check den = utorok
    //      check pocet studentov = 0

    // find po starte

    // 1.6  getxml(XXX) 
    //      check - nesmie nic vratit ani spadnut
    // 1.7. poststudent(MAT, Pako)
    //      getxml(MAT)
    //      check pocet studentov = 1    
    // 1.8. opakovany poststudent(MAT, Pako)
    //      getxml(MAT)
    //      check pocet studentov = 1 
    // 1.9. dalsi poststudent(MAT, Pako2)
    //      getxml(MAT)
    //      check pocet studentov = 2
    
    // PostXML preveri ci predmet uz nenexistuje
    // 2.1   PostXML(PP1) sobota, Stano1, Stano2, Stano3
    //      check vrati PP1
    //      getxml(PP1)
    //      check predmet = PP1
    //      check cas = sobota
    //      check pocet studentov = 3 

    // 2.2 poststudent(PP1, Pako)
    //      getxml(PP1)
    //      check pocet studentov = 4    
    // 2.4. opakovany poststudent(MAT, Stano1)
    //      getxml(PP1)
    //      check pocet studentov = 4 (bez zmeny)

    // Opakovany PostXML 
    // 3   PostXML(PP1) piatok
    //      check vrati CHYBA
    //      getxml(PP1)
    //      check cas = sobota
    //      check pocet studentov = 4 (bez zmeny)    
    
    // post(PP2) sobota, Stano1
    // find 1 predmet (Stano1)
    // find 2 predmety (Stano2)
    // find ziadny predmet (Nikto)
    

    
////////////////////////////////////////////////////////////////////////////////
    // 1. po starte existuje jediny resource pre skusku z MAT v utorok.
    // 1.1  getxml(MAT)
    //      check not null
    // 1.2     check predmet = MAT
    // 1.3     check cas = utorok
    // 1.4     check pocet studentov = 0
    
    private static Data d1;
    
    // po spusteni aplikacie skor ako klient nastavi info pomocou putInfo 
    // alebo putXml, musi getIngo vratit retazec ZIADNE
    @Test
    public void UT11_GetMATPoInite_1B() {
       d1 = null;
        try {
            d1 = client.getXml(Data.class, "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadne MAT",d1);
    }
    
    @Test
    public void UT12_PredmetPoInite_1B() {
        assertNotNull("Ziadne MAT",d1);
        assertEquals("MAT",d1.getPredmet());
    }
    
    @Test
    public void UT13_CasPoInite_1B() {
        assertNotNull("Ziadne MAT",d1);
        assertEquals("streda",d1.getDen());
    }
    
//    @Test
//    public void UT14_SizePoInite_1B() {
//        assertNotNull("Ziadne MAT",d1);
//        assertEquals(0,d1.getStudent().size());
//    }

    @Test
    public void UT14_PocetPoInite_1B() {
       long p = -1L;
        try {
            p = client.getPocet(Long.class, "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(0L,p);
    }
    
    // 1.5  getxml(XXX) 
    //      check - nesmie nic vratit ani spadnut
    @Test
    public void UT15_GetXXXPoInite_1B() {
       Data d = null;
        try {
            d = client.getXml(Data.class, "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNull(d);
    }

    // 1.6 find po starte nic nevrati
    @Test
    public void UT16_FindPoInite_1B() {
        String r = "-";
        try {
            r = client.find("NIKTO");
        } catch (Exception e) {
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            // mozno vypustit???
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) 
                fail("CHYBA WEB???: " + e.getMessage());
        }
        assertTrue("RVal: " + r,  (r==null || r.isEmpty()));
    }

    // 1.7. poststudent(MAT, Pako)
    //      getxml(MAT)
    //      check pocet studentov = 1    
    @Test
    public void UT17_PostStudentPoInite_1B() {
        try {
            client.postStudent("Pako", "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
       long p = -1L;
        try {
            p = client.getPocet(Long.class, "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(1L,p);
    }

    // 1.8. opakovany poststudent(MAT, Pako)
    //      getxml(MAT)
    //      check pocet studentov = 1 
    @Test
    public void UT18_PostOpakovanyPoInite_1B() {
        try {
            client.postStudent("Pako", "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
       long p = -1L;
        try {
            p = client.getPocet(Long.class, "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(1L,p);
    }
    
    
    // 1.9. dalsi poststudent(MAT, Pako2)
    //      getxml(MAT)
    //      check pocet studentov = 2
    @Test
    public void UT19_PostDalsiPoInite_1B() {
        try {
            client.postStudent("Pako2", "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
       long p = -1L;
        try {
            p = client.getPocet(Long.class, "MAT");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(2L,p);
    }

    // PostXML preveri ci predmet uz nenexistuje
    // 2.1   PostXML(PP1) sobota, Stano1, Stano2, Stano3
    //      check vrati PP1
    //      getxml(PP1)
    //      check cas = sobota
    //      check student.size = 3 
    //      check pocet studentov = 3 
    @Test
    public void UT21_PostPredmet1_1B() {
        Data d = new Data();
        d.setPredmet("PP1");
        d.setDen("sobota");
        d.getStudent().add("Stano1");
        d.getStudent().add("Stano2");
        d.getStudent().add("Stano3");
        
        String r = "-";
        try {
            r = client.postXml(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
        assertEquals("PP1",r);
    }
    
    static private Data pp1;

    @Test
    public void UT22_GetPredmet1_0B() {
       pp1 = null;
        try {
            pp1 = client.getXml(Data.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadne PP1",pp1);
    }
    
    @Test
    public void UT23_Predmet1Cas_0B() {
        assertNotNull("Ziadne PP1",pp1);
        assertEquals("sobota",pp1.getDen());
    }
    
    
    @Test
    public void UT24_Predmet1Size_1B() {
        assertNotNull("Ziadne PP1",pp1);
        assertEquals(3,pp1.getStudent().size());
    }
    
    @Test
    public void UT25_Predmet1Pocet_1B() {
       long npp1 = -1L;
        try {
            npp1 = client.getPocet(Long.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(3L,npp1);
    }
   
    // 2.6 poststudent(PP1, Pako)
    //      getxml(PP1)
    //      check pocet studentov = 4   
    @Test
    public void UT26_Predmet1PostStudent_1B() {
        try {
            client.postStudent("Pako", "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
       long p = -1L;
        try {
            p = client.getPocet(Long.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(4L,p);
    }
    
    // 2.7. opakovany poststudent(MAT, Stano1)
    //      getxml(PP1)
    //      check pocet studentov = 4 (bez zmeny)
    @Test
    public void UT27_Predmet1PostStano1_1B() {
        try {
            client.postStudent("Stano1", "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
       long p = -1L;
        try {
            p = client.getPocet(Long.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(4L,p);
    }
    
    // Opakovany PostXML 
    // 3   PostXML(PP1) piatok
    //      check vrati CHYBA
    //      getxml(PP1)
    //      check cas = sobota
    //      check pocet studentov = 4 (bez zmeny)    
    @Test
    public void UT31_PostZnovuPredmet1_0B() {
        Data d = new Data();
        d.setPredmet("PP1");
        d.setDen("piatok");
        
        String r = "-";
        try {
            r = client.postXml(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
        assertEquals("NIC",r);
    }
    
    @Test
    public void UT32_GetZnovuPredmet1_1B() {
       Data d = null;
        try {
            d = client.getXml(Data.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadne PP1",d);
        assertEquals("sobota",d.getDen());
    }
    
    @Test
    public void UT33_ZnovuPredmet1Pocet_1B() {
       long npp1 = -1L;
        try {
            npp1 = client.getPocet(Long.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertTrue("Zly pocet: " + npp1, npp1>0);
    }
    
    
    // post(PP2) sobota, Stano1
    @Test
    public void UT41_PostPredmet2_0B() {
        Data d = new Data();
        d.setPredmet("PP2");
        d.setDen("sobota");
        d.getStudent().add("Stano2");
        
        String r = "-";
        try {
            r = client.postXml(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
    }
    
    // find 1 predmet (Stano1)
    @Test
    public void UT42_Find1_1B() {
        String r = "-";
        try {
            r = client.find("Stano1");
        } catch (Exception e) {
                fail("CHYBA WEB: " + e.getMessage());
        }
        assertTrue("RVal: " + r,  r.contains("PP1") && !r.contains("PP2"));
    }
    
    // find 2 predmety (Stano2)
    @Test
    public void UT43_Find2_1B() {
        String r = "-";
        try {
            r = client.find("Stano2");
        } catch (Exception e) {
                fail("CHYBA WEB: " + e.getMessage());
        }
        assertTrue("RVal: " + r,  r.contains("PP1") && r.contains("PP2"));
    }
    
    // 1.6 find po starte nic nevrati
    @Test
    public void UT44_FindNikto_1B() {
        String r = "-";
        try {
            r = client.find("NIKTO");
        } catch (Exception e) {
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            // mozno vypustit???
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) 
                fail("CHYBA WEB???: " + e.getMessage());
        }
        assertTrue("RVal: " + r,  (r==null || r.isEmpty()));
    }

   

    
    
    


    
    
    
    
}
