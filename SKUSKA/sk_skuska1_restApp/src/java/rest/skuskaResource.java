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
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
@Path("skuska")
@Singleton
public class skuskaResource {


    @Context
    private UriInfo context;

    private List<Skuska> skusky;
    /**
     * Creates a new instance of skuskaResource
     */
    public skuskaResource() {
        //vytvor skusku bez studentov
        skusky = new ArrayList<>();

        Skuska sk = new Skuska("MAT", "streda");

        skusky.add(sk);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String newSkuska(Skuska sk) {
        if (sk == null) {
            return null;
        }

        try {
            for (Skuska current : skusky) {
                if (current.getPredmet().equals(sk.getPredmet())) {
                    return "NIC";
                }
            }

        } catch (NullPointerException e) {
            return "NIC";
        }

        skusky.add(sk);
        return sk.getPredmet();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{predmet}")
    public String getSkCount(@PathParam("predmet") String predmet) {
        try {
            for (Skuska current : skusky) {
                if (current.getPredmet().equals(predmet)) {
                    return current.getStudent().size() + "";
                }
            }
            return "0";//nenajdene
        } catch (NullPointerException e) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{predmet}")
    public Skuska getSkuska(@PathParam("predmet") String predmet) {
        try {
            for (Skuska current : skusky) {
                if (current.getPredmet().equals(predmet)) {
                    return current;
                }
            }
            return null;//nenajdene
        } catch (NullPointerException e) {
            return null;
        }
    }
    //ak dam pri void produces sluzba nejde
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{predmet}")
    public void addStudent(@PathParam("predmet") String predmet, String student) {
        try {
            for (Skuska current : skusky) {
                if (current.getPredmet().equals(predmet)) {
                    if (current.getStudent().contains(predmet)) {
                        return;
                    } else {
                        if(current.getStudent().contains(student)){
                            return;
                        }
                        current.getStudent().add(student);
                    }

                }
            }
            return ;//nenajdene
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Retrieves representation of an instance of rest.skuskaResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText( @QueryParam( "student") String student) {
        //TODO return proper representation object
        StringBuilder SB = new StringBuilder();
        try {
            for (Skuska currSk : skusky) {
                if(currSk.getStudent().contains(student)){
                    SB.append(currSk.getPredmet() + " ");
                }
            }
        } catch (Exception e) {
            System.out.println("exceptions was occured " + e.toString());
        }
        return SB.toString();
        
    }

    /**
     * PUT method for updating or creating an instance of skuskaResource
     *
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.TEXT_PLAIN)
//    public void putText(String content) {
//    }
}
