package clientTest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JedalenClientTest {

    static private Server jettyServer;

//    static private String key1 = null;
//    static private String key2 = null;
//    static private Poistenie z1 = null;
//    static private Poistenie z2 = null;
    static private boolean od0 = false;
    static private int ponPocet = 0;
    static private int utPocet = 0;

    public JedalenClientTest() {
    }

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
//            System.out.println("Jetty started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    static public void tearDown() {
        try {
//            System.out.println("Jetty stopping...");
            jettyServer.stop();
//       jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // pocet pondelok => 1
    //                   0 : asi zabudol vlozit syr 
    @Test
    public void UT01_pon_pocet_2b() {
        JedalenClient client = new JedalenClient();
        Integer r = null;
        try {
            r = client.getPocet(Integer.class, "pondelok");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r);
        ponPocet = r;
        assertEquals(1, ponPocet);
    }

    // pocet utorok => 0
    @Test
    public void UT02_ut_pocet_1b() {
        JedalenClient client = new JedalenClient();
        Integer r = null;
        try {
            r = client.getPocet(Integer.class, "utorok");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r);
        utPocet = r;
        assertEquals(0, utPocet);
    }

    // pocet XXX => HTTP 40*, HTTP 20* , null alebo 0
    @Test
    public void UT03_XXX_pocet_1b() {
        JedalenClient client = new JedalenClient();
        Integer r = null;
        try {
            r = client.getPocet(Integer.class, "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB:" + e.getMessage());
            }
        }
        if (r != null) {
            assertEquals(0, r.intValue());
        }
    }

    // menu pon
    @Test
    public void UT04_pon_menu_1b() {
        JedalenClient client = new JedalenClient();
        Menu m = null;
        try {
            m = client.getMenu(Menu.class, "pondelok");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(m);
        if (ponPocet > 0) {
            assertNotNull(m.getJedlo());
            assertNotNull(m.getJedlo().get(0));
            assertTrue("zly nazov: " + m.getJedlo().get(0).getNazov(), m.getJedlo().get(0).getNazov().contains("syr"));
        }

    }

    // pon.0 get jedlo (overit pocitanie od0)
    // pon.1 get jedlo 
    // pon.99 get jedlo 
    @Test
    public void UT05_pon_jedlo0_1b() {
        JedalenClient client = new JedalenClient();
        Jedlo j = null;
        try {
            j = client.getJedlo(Jedlo.class, "pondelok", "0");
        } catch (Exception e) {
            //neocakavana chyba");
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB:" + e.getMessage());
            }
        }
        if (j != null) {
            od0 = j.getNazov().contains("syr");
            fail("CHYBA: Jedlo(0) = " + j.getNazov());
        }
    }

    @Test
    public void UT06_pon_jedlo1_1b() {
        if (od0) {
            return;
        }
        if (ponPocet == 0) {
            return;
        }
        
        JedalenClient client = new JedalenClient();
        Jedlo j = null;
        try {
            j = client.getJedlo(Jedlo.class, "pondelok", "1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(j);
        assertTrue("zly nazov: " + j.getNazov(), j.getNazov().contains("syr"));
    }

    @Test
    public void UT07_pon_jedlo99_1b() {
        JedalenClient client = new JedalenClient();
        Jedlo j = null;
        try {
            j = client.getJedlo(Jedlo.class, "pondelok", "99");
        } catch (Exception e) {
            //neocakavana chyba");
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB:" + e.getMessage());
            }
        }
        assertNull(j);
    }

    // pon post Palacinky
    @Test
    public void UT08_pon_post_1b() {
        Integer r = null;
        JedalenClient client = new JedalenClient();
        Jedlo pj1 = new Jedlo("Palacinky", 3.1);
        try {
            client.postJedlo(pj1, "pondelok");
            r = client.getPocet(Integer.class, "pondelok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertNotNull(r);
        int expected = ponPocet + 1;
        ponPocet = r;
        assertEquals(expected, ponPocet);

    }

    // pon post Palacinky - zmena ceny
    @Test
    public void UT09_pon_postM_2b() {
        Integer r = null;
        JedalenClient client = new JedalenClient();
        Jedlo pj1 = new Jedlo("Palacinky", 4.9);
        try {
            client.postJedlo(pj1, "pondelok");
            r = client.getPocet(Integer.class, "pondelok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertNotNull(r);
        int expected = ponPocet;
        ponPocet = r;
        assertEquals(expected, ponPocet);
    }

    @Test
    public void UT10_pon_jedlo2_2b() {
        JedalenClient client = new JedalenClient();
        Jedlo j = null;
        
        int n = ponPocet;
        if (od0) {
            n = n - 1;
        }

        try {
            j = client.getJedlo(Jedlo.class, "pondelok", "" + n);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("CHYBA: Jedlo null", j);
        assertEquals("Palacinky", j.getNazov());
        assertEquals(4.9, j.getCena(), 0.05);
    }

    // ut post nj2 jedlo
    // ut post nj3 jedlo
    // ut pocet >2
    // ut.2 get jedlo 
    // ut post Rezen, File
    @Test
    public void UT11_ut_post_1b() {
        Integer r = null;
        JedalenClient client = new JedalenClient();
        Jedlo uj1 = new Jedlo("Rezen", 4.1);
        Jedlo uj2 = new Jedlo("File", 4.1);
        try {
            client.postJedlo(uj1, "utorok");
            client.postJedlo(uj2, "utorok");
            r = client.getPocet(Integer.class, "utorok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertNotNull(r);
        assertEquals(utPocet + 2, r.intValue());
    }

    // ut delete 1
    @Test
    public void UT12_ut_delete_1b() {
        Integer r = null;
        JedalenClient client = new JedalenClient();
        try {
            client.delJedlo("utorok", "1");
            r = client.getPocet(Integer.class, "utorok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertNotNull(r);
        assertEquals(utPocet + 1, r.intValue());
    }

}
