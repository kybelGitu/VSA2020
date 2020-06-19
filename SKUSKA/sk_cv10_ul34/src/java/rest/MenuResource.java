/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import data.DayObj;
import data.Jedlo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Peter
 */
@Path("menu")
@Singleton
public class MenuResource {

    Map<String, DayObj> menuMap;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MenuResource
     */
    public MenuResource() {
        this.menuMap = new LinkedHashMap<String, DayObj>();

        this.menuMap.put("Pon", new DayObj());
        this.menuMap.put("Ut", new DayObj());
        this.menuMap.put("Str", new DayObj());
        this.menuMap.put("Stv", new DayObj());
        this.menuMap.put("Pia", new DayObj());
        
        Jedlo j1 = new Jedlo("bolonske makarony ", 4.5);

        this.menuMap.get("Pon").getJedla().put(1, j1);
        this.menuMap.get("Pon").setDen("Pon");
        
        Jedlo j1a = new Jedlo("bolonske makove", 4.5);

        this.menuMap.get("Pon").getJedla().put(2, j1a);
        this.menuMap.get("Pon").setDen("Pon");
        
        Jedlo j2 = new Jedlo("kecupove makarony ", 4.5);

        this.menuMap.get("Ut").getJedla().put(1, j2);
        this.menuMap.get("Ut").setDen("Ut");
        
        Jedlo j3 = new Jedlo("horcicove makarony ", 4.5);

        this.menuMap.get("Str").getJedla().put(1, j3);
        this.menuMap.get("Str").setDen("Str");
        
        Jedlo j4 = new Jedlo("barbeky makarony ", 4.5);

        this.menuMap.get("Stv").getJedla().put(1, j4);
        this.menuMap.get("Stv").setDen("Stv");
        
        Jedlo j5 = new Jedlo("sipeky makarony ", 4.5);

        this.menuMap.get("Pia").getJedla().put(1, j5);
        this.menuMap.get("Pia").setDen("Pia");
        
        Jedlo j5a = new Jedlo("pokazeny spenat", 2.5);

        this.menuMap.get("Pia").getJedla().put(99, j5a);
        this.menuMap.get("Pia").setDen("Pia");
        
        System.out.println("First dafault element construcdet, "
                + "you can get hit with get method");

    }

    /**
     * Retrieves representation of an instance of rest.MenuResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        System.out.println("ide to");
        return "app ready to use";
    }

//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public Jedlo getTestEat() {
//        //TODO return proper representation object
//        System.out.println("first est will be executed");
//        return menuMap.get("Pon").getJedla().get(1);//get first dish
//    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List <DayObj> getfullMenuXml() {
        //TODO return proper representation object
        List <DayObj> l =  new ArrayList(menuMap.values());
        System.out.println("velksot listu  je " + l.size());
        for(DayObj d : l ){
            System.out.println("object: "  + 
                    "dem: " + d.getDen() + " daco : " + d.getJedalnyList_ok().toString());
        }
        return l;//get first dish
    }

    //get DAY MANU
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{day:Pon|Ut|Str|Stv|Pia}")
    public List <Jedlo> getDailyMenu(@PathParam("day") String day) {
        //TODO return proper representation object
        try {
            System.out.println("uz ide denne muenu - ponuka dna");
            List <Jedlo> jedalnyList_ok = new ArrayList( menuMap.get(day).getJedalnyList_ok());
            return jedalnyList_ok;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{day:Pon|Ut|Str|Stv|Pia}/{index:[0-9]+}")//alebo.*
    public Jedlo getJedloChoice(@PathParam("day") String day,
                                       @PathParam("index") int index  ) {
        //TODO return proper representation object
        try {
            DayObj currentDay= menuMap.get(day);
            Jedlo yourChoice = currentDay.getJedla().get(index);
            System.out.println("asdasdasdas");
            return yourChoice; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        return null;
    }

    /**
     * PUT method for updating or creating an instance of MenuResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("{day:Pon|Ut|Str|Stv|Pia}/{index:[0-9]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putText(@PathParam("day") String day,
                                       @PathParam("index") int index ,Jedlo vec) {
        try {
            //if map contains key, replace him
            System.out.println("pridavam vec " +  vec.toString() + " do " + index);
            menuMap.get(day).getJedla().put(index, vec);
        }
        catch (Exception e) {
            System.out.println("exceptiopn was occured");
            System.out.println(e.toString()); 
        }
    }
//    nejde cez testovanie pomocuo netbeans!!!!
    @DELETE
    @Path("{day:Pon|Ut|Str|Stv|Pia}/{index:[0-9]+}")
    public void deleteJedlo(@PathParam("day") String day,
                                       @PathParam("index") int index) {
        try {
            System.out.println("mazem " );
            menuMap.get(day).getJedla().remove(index);
        }
        catch (Exception e) {
            System.out.println("exceptiopn was occured");
            System.out.println(e.toString()); 
        }
    }
    
    
}
