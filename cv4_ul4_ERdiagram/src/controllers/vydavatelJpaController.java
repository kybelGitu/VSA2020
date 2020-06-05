/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.vydavatel;
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
public class vydavatelJpaController implements Serializable {

    public vydavatelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(vydavatel vydavatel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vydavatel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(vydavatel vydavatel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vydavatel = em.merge(vydavatel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vydavatel.getId();
                if (findvydavatel(id) == null) {
                    throw new NonexistentEntityException("The vydavatel with id " + id + " no longer exists.");
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
            vydavatel vydavatel;
            try {
                vydavatel = em.getReference(vydavatel.class, id);
                vydavatel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vydavatel with id " + id + " no longer exists.", enfe);
            }
            em.remove(vydavatel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<vydavatel> findvydavatelEntities() {
        return findvydavatelEntities(true, -1, -1);
    }

    public List<vydavatel> findvydavatelEntities(int maxResults, int firstResult) {
        return findvydavatelEntities(false, maxResults, firstResult);
    }

    private List<vydavatel> findvydavatelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(vydavatel.class));
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

    public vydavatel findvydavatel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(vydavatel.class, id);
        } finally {
            em.close();
        }
    }

    public int getvydavatelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<vydavatel> rt = cq.from(vydavatel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
