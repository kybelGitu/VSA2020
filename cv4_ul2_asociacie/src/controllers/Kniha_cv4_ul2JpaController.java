/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Kniha_cv4_ul2;
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
public class Kniha_cv4_ul2JpaController implements Serializable {

    public Kniha_cv4_ul2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kniha_cv4_ul2 kniha_cv4_ul2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kniha_cv4_ul2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kniha_cv4_ul2 kniha_cv4_ul2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kniha_cv4_ul2 = em.merge(kniha_cv4_ul2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = kniha_cv4_ul2.getId();
                if (findKniha_cv4_ul2(id) == null) {
                    throw new NonexistentEntityException("The kniha_cv4_ul2 with id " + id + " no longer exists.");
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
            Kniha_cv4_ul2 kniha_cv4_ul2;
            try {
                kniha_cv4_ul2 = em.getReference(Kniha_cv4_ul2.class, id);
                kniha_cv4_ul2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kniha_cv4_ul2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(kniha_cv4_ul2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kniha_cv4_ul2> findKniha_cv4_ul2Entities() {
        return findKniha_cv4_ul2Entities(true, -1, -1);
    }

    public List<Kniha_cv4_ul2> findKniha_cv4_ul2Entities(int maxResults, int firstResult) {
        return findKniha_cv4_ul2Entities(false, maxResults, firstResult);
    }

    private List<Kniha_cv4_ul2> findKniha_cv4_ul2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kniha_cv4_ul2.class));
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

    public Kniha_cv4_ul2 findKniha_cv4_ul2(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kniha_cv4_ul2.class, id);
        } finally {
            em.close();
        }
    }

    public int getKniha_cv4_ul2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kniha_cv4_ul2> rt = cq.from(Kniha_cv4_ul2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
