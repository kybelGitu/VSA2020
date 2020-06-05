/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3d_jpa_controller;

import cv3d_jpa_controller.exceptions.NonexistentEntityException;
import cv3d_jpa_controller.exceptions.PreexistingEntityException;
import entities.Osoba3cvUl2;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author vsa
 */
public class Osoba3cvUl2JpaController implements Serializable {

    public Osoba3cvUl2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Osoba3cvUl2 osoba3cvUl2) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(osoba3cvUl2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOsoba3cvUl2(osoba3cvUl2.getId()) != null) {
                throw new PreexistingEntityException("Osoba3cvUl2 " + osoba3cvUl2 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public  void edit(Osoba3cvUl2 osoba3cvUl2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            osoba3cvUl2 = em.merge(osoba3cvUl2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = osoba3cvUl2.getId();
                if (findOsoba3cvUl2(id) == null) {
                    throw new NonexistentEntityException("The osoba3cvUl2 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Osoba3cvUl2 osoba3cvUl2;
            try {
                osoba3cvUl2 = em.getReference(Osoba3cvUl2.class, id);
                osoba3cvUl2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The osoba3cvUl2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(osoba3cvUl2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Osoba3cvUl2> findOsoba3cvUl2Entities() {
        return findOsoba3cvUl2Entities(true, -1, -1);
    }

    public List<Osoba3cvUl2> findOsoba3cvUl2Entities(int maxResults, int firstResult) {
        return findOsoba3cvUl2Entities(false, maxResults, firstResult);
    }

    private List<Osoba3cvUl2> findOsoba3cvUl2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Osoba3cvUl2.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Osoba3cvUl2 findOsoba3cvUl2(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Osoba3cvUl2.class, id);
        } finally {
            em.close();
        }
    }

    public int getOsoba3cvUl2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Osoba3cvUl2> rt = cq.from(Osoba3cvUl2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
