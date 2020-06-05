/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv4_ul4_erdiagram;

import entities.*;
import controllers.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class Cv4_ul4_ERdiagram {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv4_ul4_ERdiagramPU");

    /**
     *
     * @return
     */
    public static EntityManagerFactory getEmf() {
        return emf;
    }

    /**
     * uloha 4 - funkcia ktora nacita autorove knihy
     *  autorove data musia byt nacitane z db
     * 
     * TODO: naciatanie vsetkyc dat
     * @param bookOwner
     * @return set of shops
     */
    public static Set<obchod> findAutoresBooksShops(autor bookOwner){
        Set <obchod> results = new HashSet<obchod>();
        try {
            List <Kniha> ownersBooks = bookOwner.getKnihy();
            for(Kniha k : ownersBooks){
                List <polozka> kniha_na_polozkach = k.getPolozky();
                for(polozka p : kniha_na_polozkach){
                    results.add(p.getObchod_at_polozka());
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        finally{
            return results;
        }
    }

    /**
     *
     */
    public static void loadDBData(){
        autorJpaController autorCntrl = new autorJpaController(emf);
        List autores = autorCntrl.findautorEntities();
    }
    
    /**
     *s[0]-isbn s[1]-vydavatel s[2]- nazov knihy s[3] - meno s[4] - shop s[5] - cena
     */
    public static void saveCsvToDB() throws Exception{
        BufferedReader br = null;
        try {
            String line;
//            br = new BufferedReader(new FileReader("data/data3.csv"));
            br = new BufferedReader(new FileReader("/home/vsa/Desktop/WORK/cv4_ul4_ERdiagram/src/data/data3.csv"));
            try {
                //create controllers
                KnihaJpaController knihaCntrl = new KnihaJpaController(emf);
                vydavatelJpaController vydavatelCntrl = new vydavatelJpaController(emf);
                autorJpaController autorCntrl = new autorJpaController(emf);
                obchodJpaController obchodCntrl = new obchodJpaController(emf);
                polozkaJpaController polozkaCntrl = new polozkaJpaController(emf);
                
                while ((line = br.readLine()) != null) {
                    //todo remove hidden characters
                    String s[] = line.split(";");
                    // kniha
                    String isbn = s[0];
                    String nazovKnihy = s[2];
                    // vydavetel
                    String vydavatelNazov = s[1];
                    //autor
                    String[] autorMeno = s[3].split(",");
                    //obchod
                    String obchodMeno = s[4];
                    //polozka
                    String cenaPolozka = s[5];
                    
                    // 1 STEP: create polozka
                    //create new objects, subor -> 1riadok - 1polozka
                    EntityManager em = emf.createEntityManager();
                    polozka p = new polozka();//polozka sa vytvara vzdy! uniqate
                    p.setCena(Double.parseDouble(cenaPolozka));
                    // 2 STEP: test if shop exist and assign him to polozka 
                    obchod o = new obchod();
                    Query q = em.createNamedQuery("obchod.findByNazov")
                            .setParameter("nazovObchodu", obchodMeno);
                    try {
                        o = (obchod) q.getSingleResult();
                    } catch(NoResultException e){
                        o.setNazov(obchodMeno);
                        obchodCntrl.create(o);//object are NOT created with polozka by cascade 
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    //assign obchod to polozka
                    p.setObchod_at_polozka(o);
                    
                    //3 STEP test if exist kniha with current isbn 
                    //if not, assign other atributes(vyhotovitel, autor)
                    Kniha k = new Kniha();
                    q = em.createNamedQuery("Kniha.findByNazov")
                            .setParameter("nazovKnihy", nazovKnihy);
                    try {//ak prejde kniha je uz nastavena
                        k = (Kniha) q.getSingleResult();
                        //save polozka, createng object is already done(autors are indepedently from polozka)
                        // none association with autores
                    } catch(NoResultException e){//kniha doesn't exists, create new
                        k.setNazov(nazovKnihy);
                        k.setIsbn(isbn);
                        //4 STEP: set vydavatel if doesn't exists create new
                        vydavatel v = new vydavatel();
                        q = em.createNamedQuery("vydavatel.findByNazov")
                            .setParameter("vydavatelNazov", vydavatelNazov);
                        try {
                            v = (vydavatel) q.getSingleResult();
                            
                        }catch(NoResultException ex){
                            //vydavatel doesn't exists -> create
                            v.setNazov(vydavatelNazov);
                            vydavatelCntrl.create(v);
                        } 
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        k.setVydavatel(v);
                        //save books to DB
                        knihaCntrl.create(k);// saving polozka is Not cascade
                    }
                   catch (Exception e) {
                        e.printStackTrace();
                    }
                    //save reference kniha to polozka and create polozka (EVER)
                    p.setKniha_at_polozka(k);
                    polozkaCntrl.create(p);
                    //5STEP: set autors, assign for each current new book
                    for (String autorName : autorMeno) {
                        //remove white spaces at begin and end of string
                        autorName = autorName.trim();
                        autor a = new autor();
                        q = em.createNamedQuery("autor.findByMeno")
                                .setParameter("autorName", autorName);
                        try {
                            a = (autor) q.getSingleResult();
                            //autor exists
                            //he have already current book?
                            // autor already existz but the book is new
                            if (a.getKnihy() != null && !a.getKnihy().contains(k)) {
//                                List<Kniha> currentBooks = a.getKnihy();
//                                currentBooks.add(k);
                                a.getKnihy().add(k);
//                                a.setKnihy(currentBooks);
                                a.setMeno(autorName);
                                // make merge autor exists
                                autorCntrl.edit(a);//exception by nemala nastat
                            }
                        } catch (NoResultException ex) {
                            //autor doesn't exists -> create
//                            List<Kniha> currentBooks = new ArrayList<Kniha>();
//                            currentBooks.add(k);
//                            a.setKnihy(currentBooks);
                            a.setKnihy((new ArrayList<Kniha>()));
                            a.getKnihy().add(k);
                            a.setMeno(autorName);
                            autorCntrl.create(a);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                                      
                }
                /**
                 * @param args the command line arguments
                 */
            } catch (IOException ex) {
                Logger.getLogger(Cv4_ul4_ERdiagram.class.getName()).log(Level.SEVERE, null, ex);
            }
 /**
             * @param args the command line arguments
             */
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cv4_ul4_ERdiagram.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Cv4_ul4_ERdiagram.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        saveCsvToDB();
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("autor.findAll");
        List <autor> lustrovani =  q.getResultList();
        for(autor lustrovanY : lustrovani){
            System.out.println("LUSTROVANY AUTOR: " + lustrovanY.getMeno());
            Set <obchod> results = findAutoresBooksShops(lustrovanY);
            for(obchod a : results){
                System.out.println("shop: ;" + a.toString());
            }
        }
        loadDBData();
    }

    /**
     *
     * @param object
     */
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv4_ul4_ERdiagramPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
