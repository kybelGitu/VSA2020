/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Peter
 */
@Singleton
@Path("skuska")
public class skuskaResource {

    @Context
    private UriInfo context;
    
    List <Skuska> skusky;
    
    

    /**
     * Creates a new instance of skuskaResource
     */
    public skuskaResource() {
        skusky = new ArrayList<Skuska>();
        Skuska s = new Skuska();
        s.setDen("streda");
        s.setPredmet("MAT");
        
        List <String> students = new ArrayList<String>();
//        students.add("Jozef Mrkvicka");
//        students.add("Janko Hrasko");
//        
        s.setStudents(students);
        
        skusky.add(s);
    }

    /**
     * Retrieves representation of an instance of rest.skuskaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSkuskyStudents(
        @QueryParam("student") String student) {
        if(student.length() == 0 )
            return "";
        
        StringBuilder sb = new StringBuilder();
        for (Skuska sk : skusky) {
            List <String> joined = sk.getStudents();
            for(String current : joined){
                if(current.equals(student)){
                    sb.append(sk.getPredmet() + " ");
                    break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * PUT method for updating or creating an instance of skuskaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String postXml(Skuska s) {
        for( Skuska sk :skusky){
            if(sk.getPredmet().equals(s.getPredmet())){
                return "NIC";
            }
        }
        System.out.println(s.toString());
        skusky.add(s);
        return s.getPredmet();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{predmet}")// TODO PRAZDNY request
    public String getCountStudents(@PathParam("predmet") String predmet) {
        System.out.println("counts predmet");
        if(predmet == null){
            System.out.println("predmet je null");
            return null;
        }
        for( Skuska sk : skusky) {
            if (sk.getPredmet().equals(predmet)) {
                return Integer.toString(sk.getStudents().size());
            }
        }
        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{predmet}")// TODO PRAZDNY request
    public Skuska getXml2(@PathParam("predmet") String predmet) {
        System.out.println("counts predmet");
        if(predmet == null){
            return null;
        }
        for( Skuska sk : skusky) {
            if (sk.getPredmet().equals(predmet)) {
                return sk;
            }
        }
        return null;
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{predmet}")// TODO PRAZDNY request
    public void pridajSa(@PathParam("predmet") String predmet ,String context) {
        System.out.println("counts predmet");
        if(predmet == null || context == null || context.length() == 0){
            return;
        }
        for( Skuska sk : skusky) {
            if (sk.getPredmet().equals(predmet)) {
                List <String> joined = sk.getStudents();
                for(String student : joined){
                    if(student.equals(context)){
                        return;//UZ JE PRIHLASENY
                    }
                }
                sk.getStudents().add(context);
                return;// PRIHLASUJEM NA PREDMET
            }
        }
        //SKUSKA NEEXISTUJE VYTVOR JU
        Skuska newSk = new Skuska();
        newSk.setPredmet(predmet);
        newSk.getStudents().add(context);//add students
        skusky.add(newSk);// dorobit array skusiek (<x>(.)<x>)
        return;
    }
    
}
