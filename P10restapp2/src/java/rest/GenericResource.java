/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import data.Jedlo;
import data.Menu;
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

//    private List<Jedlo> jedla;
    Menu menu;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
//        jedla = new ArrayList<>();
//        jedla.add(new Jedlo("Vyprazany syr", 3.5));
        menu = new Menu();
        menu.getJedla().add(new Jedlo("Vyprazany syr", 3.5));
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
        return "" + menu.getJedla().size();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Menu getMenu() {
        //TODO return proper representation object
        return menu;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String pridajJedlo(Jedlo content) {
        menu.getJedla().add(content);
        return "" + (menu.getJedla().size()-1);
    }

    @GET
    @Path("{cislo}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedlo(@PathParam("cislo") int n) {
        if (n < 0 || n >= menu.getJedla().size()) {
            return null;
        }
        return menu.getJedla().get(n);        
    }

    @DELETE
    @Path("{cislo}")
    public void odstranJedlo(@PathParam("cislo") int n) {
        if (n < 0 || n >= menu.getJedla().size()) {
            return;
        }
        menu.getJedla().remove(n);
    }

    
    
}
