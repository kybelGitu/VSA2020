/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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

    private Map<String, String> jedla;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        jedla = new HashMap<>();
        jedla.put("Syr", "Vyprazany syr, 3.50");
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMenu() {
        String r = "";
        for (String s : jedla.keySet()) {
           r = r + s + " "; 
        }
        return r;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("{nazov}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void pridajJedlo(@PathParam("nazov") String n, String content) {
        jedla.put(n, content);
    }

    @GET
    @Path("{nazov}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getJedlo(@PathParam("nazov") String n) {
        return jedla.get(n);
    }

    @DELETE
    @Path("{nazov}")
    public void odstranJedlo(@PathParam("nazov") String n) {
        jedla.remove(n);
    }

    
    
}
