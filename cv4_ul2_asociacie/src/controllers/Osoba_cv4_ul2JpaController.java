/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Osoba_cv4_ul2;
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
public class Osoba_cv4_ul2JpaController implements Serializable {

    public Osoba_cv4_ul2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Osoba_cv4_ul2 osoba_cv4_ul2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(osoba_cv4_ul2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Osoba_cv4_ul2 osoba_cv4_ul2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            osoba_cv4_ul2 = em.merge(osoba_cv4_ul2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = osoba_cv4_ul2.getId();
                if (findOsoba_cv4_ul2(id) == null) {
                    throw new NonexistentEntityException("The osoba_cv4_ul2 with id " + id + " no longer exists.");
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
            Osoba_cv4_ul2 osoba_cv4_ul2;
            try {
                osoba_cv4_ul2 = em.getReference(Osoba_cv4_ul2.class, id);
                osoba_cv4_ul2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The osoba_cv4_ul2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(osoba_cv4_ul2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Osoba_cv4_ul2> findOsoba_cv4_ul2Entities() {
        return findOsoba_cv4_ul2Entities(true, -1, -1);
    }

    public List<Osoba_cv4_ul2> findOsoba_cv4_ul2Entities(int maxResults, int firstResult) {
        return findOsoba_cv4_ul2Entities(false, maxResults, firstResult);
    }

    private List<Osoba_cv4_ul2> findOsoba_cv4_ul2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Osoba_cv4_ul2.class));
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

    public Osoba_cv4_ul2 findOsoba_cv4_ul2(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Osoba_cv4_ul2.class, id);
        } finally {
            em.close();
        }
    }

    public int getOsoba_cv4_ul2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Osoba_cv4_ul2> rt = cq.from(Osoba_cv4_ul2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
