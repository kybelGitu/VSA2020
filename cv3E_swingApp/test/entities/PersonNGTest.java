/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import cv3swingapp.Person;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author vsa
 */
public class PersonNGTest {
    
    public PersonNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getFirstName method, of class Person.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Person instance = new Person();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Person.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Person instance = new Person();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBirthYear method, of class Person.
     */
    @Test
    public void testGetBirthYear() {
        System.out.println("getBirthYear");
        Person instance = new Person();
        int expResult = 0;
        int result = instance.getBirthYear();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmployed method, of class Person.
     */
    @Test
    public void testIsEmployed() {
        System.out.println("isEmployed");
        Person instance = new Person();
        boolean expResult = false;
        boolean result = instance.isEmployed();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class Person.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "";
        Person instance = new Person();
        instance.setFirstName(firstName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class Person.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "";
        Person instance = new Person();
        instance.setLastName(lastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBirthYear method, of class Person.
     */
    @Test
    public void testSetBirthYear() {
        System.out.println("setBirthYear");
        int birthYear = 0;
        Person instance = new Person();
        instance.setBirthYear(birthYear);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmployed method, of class Person.
     */
    @Test
    public void testSetEmployed() {
        System.out.println("setEmployed");
        boolean employed = false;
        Person instance = new Person();
        instance.setEmployed(employed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Person instance = new Person();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Person.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Person instance = new Person();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
