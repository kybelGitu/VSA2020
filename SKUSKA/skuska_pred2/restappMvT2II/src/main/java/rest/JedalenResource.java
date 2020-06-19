/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojo.Jedlo;
import pojo.Menu;

/**
 * REST Web Service
 *
 * @author igor
 */
@Path("menu")
@Singleton
public class JedalenResource {

    @Context
    private UriInfo context;

    private Map<String, Menu> ponuka;

    /**
     * Creates a new instance of JedalenResource
     */
    public JedalenResource() {
        ponuka = new HashMap<>();
        ponuka.put("pondelok", new Menu());
        ponuka.put("utorok", new Menu());
        ponuka.put("streda", new Menu());
        ponuka.put("stvrtok", new Menu());
        ponuka.put("piatok", new Menu());
        ponuka.put("sobota", new Menu());
        ponuka.put("nedela", new Menu());

        Jedlo j1 = new Jedlo("Vyprazany syr", 3.5);
        ponuka.get("pondelok").getJedlo().add(j1);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{den : [a-z]+}")
    public Menu getMenu(@PathParam("den") String den) {
        return ponuka.get(den);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{den : [a-z]+}")
    public String getPocet(@PathParam("den") String den) {
        Menu m = ponuka.get(den);
        if (m != null) {
            return "" + m.getJedlo().size();
        }
        return null;
    }

    @POST
    @Path("{den : [a-z]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void postJedlo(Jedlo content, @PathParam("den") String den) {
//        System.out.println("POST JEDLO" + content);
        Menu m = ponuka.get(den);
        if (m != null) {
            for (Jedlo j : m.getJedlo()) {
                if (j.getNazov().equals(content.getNazov())) {
                    j.setCena(content.getCena());
                    return;
                }
            }
            m.getJedlo().add(content);
        }
    }

//    // nie je nutne
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("{den}/{n}")
//    public String getNazov(@PathParam("den") String den, @PathParam("n") int n) {
//        Menu m = ponuka.get(den);
//        if (m!=null) {
//            if (n < 1 || n > m.getJedlo().size()) 
//                return null;
//            return m.getJedlo().get(n-1).getNazov();
//        }
//        return null;
//    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{den}/{n}")
    public Jedlo getJedlo(@PathParam("den") String den, @PathParam("n") int n) {
//        n = n + 1;
        Menu m = ponuka.get(den);
        if (m != null) {
            if (n < 1 || n > m.getJedlo().size()) {
                return null;
            }
            return m.getJedlo().get(n - 1);
        }
        return null;
    }

    @DELETE
    @Path("{den}/{n}")
    public void delJedlo(@PathParam("den") String den, @PathParam("n") int n) {
//        n = n + 1;
        Menu m = ponuka.get(den);
        if (m != null) {
            if (n < 1 || n > m.getJedlo().size()) {
                return;
            }
            m.getJedlo().remove(n - 1);
        }
    }

}
