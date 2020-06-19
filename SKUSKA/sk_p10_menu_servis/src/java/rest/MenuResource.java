/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import data.Jedlo;
import java.util.Collection;
import java.util.TreeMap;
import javax.ejb.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Peter
 */
@Singleton
@Path("Menu")
public class MenuResource {

    @Context
    private UriInfo context;

    TreeMap<Long, Jedlo> menu;

    public MenuResource() {
        menu = new TreeMap<>();
        menu.put(1l, new Jedlo("Halusky", 2.5));
        menu.put(2l, new Jedlo("Gulas", 2.5));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Long getTest() {
        return 99l;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Jedlo> getMenu() {
        return menu.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Long postXml(Jedlo content) {
        long index = 1;
        System.out.println(content.toString());
        if (!menu.isEmpty()) {
            index = menu.lastKey() + 1;
        }

        menu.put(index, content);
        return index;
    }

    @GET
    @Path("{index}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getXml(@PathParam("index") long index) {
        return menu.get(index);
    }

    @PUT
    @Path("{index}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(@PathParam("index") long index, Jedlo content) {
        menu.replace(index, content);
    }

    @DELETE
    @Path("{index}")
    public Jedlo delete(@PathParam("index") long index) {
        return menu.remove(index);
    }
}