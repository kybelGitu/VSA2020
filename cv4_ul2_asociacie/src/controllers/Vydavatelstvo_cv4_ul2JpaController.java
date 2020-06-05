/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Vydavatelstvo_cv4_ul2;
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
public class Vydavatelstvo_cv4_ul2JpaController implements Serializable {

    public Vydavatelstvo_cv4_ul2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vydavatelstvo_cv4_ul2 vydavatelstvo_cv4_ul2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vydavatelstvo_cv4_ul2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vydavatelstvo_cv4_ul2 vydavatelstvo_cv4_ul2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vydavatelstvo_cv4_ul2 = em.merge(vydavatelstvo_cv4_ul2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vydavatelstvo_cv4_ul2.getId();
                if (findVydavatelstvo_cv4_ul2(id) == null) {
                    throw new NonexistentEntityException("The vydavatelstvo_cv4_ul2 with id " + id + " no longer exists.");
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
            Vydavatelstvo_cv4_ul2 vydavatelstvo_cv4_ul2;
            try {
                vydavatelstvo_cv4_ul2 = em.getReference(Vydavatelstvo_cv4_ul2.class, id);
                vydavatelstvo_cv4_ul2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vydavatelstvo_cv4_ul2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(vydavatelstvo_cv4_ul2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vydavatelstvo_cv4_ul2> findVydavatelstvo_cv4_ul2Entities() {
        return findVydavatelstvo_cv4_ul2Entities(true, -1, -1);
    }

    public List<Vydavatelstvo_cv4_ul2> findVydavatelstvo_cv4_ul2Entities(int maxResults, int firstResult) {
        return findVydavatelstvo_cv4_ul2Entities(false, maxResults, firstResult);
    }

    private List<Vydavatelstvo_cv4_ul2> findVydavatelstvo_cv4_ul2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vydavatelstvo_cv4_ul2.class));
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

    public Vydavatelstvo_cv4_ul2 findVydavatelstvo_cv4_ul2(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vydavatelstvo_cv4_ul2.class, id);
        } finally {
            em.close();
        }
    }

    public int getVydavatelstvo_cv4_ul2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vydavatelstvo_cv4_ul2> rt = cq.from(Vydavatelstvo_cv4_ul2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
