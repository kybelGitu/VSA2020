/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vsa
 */
public class PersonSwingappJpaControllerTest {
    
    public PersonSwingappJpaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class PersonSwingappJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        PersonSwingappJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class PersonSwingappJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        PersonSwingapp personSwingapp = null;
        PersonSwingappJpaController instance = null;
        instance.create(personSwingapp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class PersonSwingappJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        PersonSwingapp personSwingapp = null;
        PersonSwingappJpaController instance = null;
        instance.edit(personSwingapp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class PersonSwingappJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Long id = null;
        PersonSwingappJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPersonSwingappEntities method, of class PersonSwingappJpaController.
     */
    @Test
    public void testFindPersonSwingappEntities_0args() {
        System.out.println("findPersonSwingappEntities");
        PersonSwingappJpaController instance = null;
        List<PersonSwingapp> expResult = null;
        List<PersonSwingapp> result = instance.findPersonSwingappEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPersonSwingappEntities method, of class PersonSwingappJpaController.
     */
    @Test
    public void testFindPersonSwingappEntities_int_int() {
        System.out.println("findPersonSwingappEntities");
        int maxResults = 0;
        int firstResult = 0;
        PersonSwingappJpaController instance = null;
        List<PersonSwingapp> expResult = null;
        List<PersonSwingapp> result = instance.findPersonSwingappEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPersonSwingapp method, of class PersonSwingappJpaController.
     */
    @Test
    public void testFindPersonSwingapp() {
        System.out.println("findPersonSwingapp");
        Long id = null;
        PersonSwingappJpaController instance = null;
        PersonSwingapp expResult = null;
        PersonSwingapp result = instance.findPersonSwingapp(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonSwingappCount method, of class PersonSwingappJpaController.
     */
    @Test
    public void testGetPersonSwingappCount() {
        System.out.println("getPersonSwingappCount");
        PersonSwingappJpaController instance = null;
        int expResult = 0;
        int result = instance.getPersonSwingappCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
