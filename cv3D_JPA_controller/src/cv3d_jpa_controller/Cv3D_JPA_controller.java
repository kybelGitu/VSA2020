/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3d_jpa_controller;

import entities.Osoba3cvUl2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Cv3D_JPA_controller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //test controller
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv3D_JPA_controllerPU");
        Osoba3cvUl2JpaController cntrl = new Osoba3cvUl2JpaController(emf);
        Osoba3cvUl2 os = new Osoba3cvUl2();
        os.setId(Long.MIN_VALUE +2);
        os.setMeno("druhy jonas2");
        try {
            cntrl.edit(os);
//            cntrl.destroy(1l);
            List<Osoba3cvUl2> ls = cntrl.findOsoba3cvUl2Entities();
            cntrl.destroy(Long.MIN_VALUE +1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    
}
