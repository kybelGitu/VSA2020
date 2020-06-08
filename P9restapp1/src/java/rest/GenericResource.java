/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author igor
 */
@Singleton
@Path("menu")
public class GenericResource {

    private List<String> jedla;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        jedla = new ArrayList<>();
        jedla.add("Vyprazany syr");
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPocet() {
        //TODO return proper representation object
        return "" + jedla.size();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String pridajJedlo(String content) {
        jedla.add(content);
        return "" + (jedla.size()-1);
    }

    @GET
    @Path("{cislo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getJedlo(@PathParam("cislo") int n) {
        if (n < 0 || n >= jedla.size()) {
            return null;
        }
        return jedla.get(n);
    }

    @DELETE
    @Path("{cislo}")
    public void odstranJedlo(@PathParam("cislo") int n) {
        if (n < 0 || n >= jedla.size()) {
            return;
        }
        jedla.remove(n);
    }

    
    
}
