/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Kniha;
import entities.polozka;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vsa
 */
public class polozkaJpaController implements Serializable {

    public polozkaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(polozka polozka) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kniha kniha_at_polozka = polozka.getKniha_at_polozka();
            if (kniha_at_polozka != null) {
                kniha_at_polozka = em.getReference(kniha_at_polozka.getClass(), kniha_at_polozka.getId());
                polozka.setKniha_at_polozka(kniha_at_polozka);
            }
            em.persist(polozka);
            if (kniha_at_polozka != null) {
                kniha_at_polozka.getPolozky().add(polozka);
                kniha_at_polozka = em.merge(kniha_at_polozka);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(polozka polozka) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            polozka persistentpolozka = em.find(polozka.class, polozka.getId());
            Kniha kniha_at_polozkaOld = persistentpolozka.getKniha_at_polozka();
            Kniha kniha_at_polozkaNew = polozka.getKniha_at_polozka();
            if (kniha_at_polozkaNew != null) {
                kniha_at_polozkaNew = em.getReference(kniha_at_polozkaNew.getClass(), kniha_at_polozkaNew.getId());
                polozka.setKniha_at_polozka(kniha_at_polozkaNew);
            }
            polozka = em.merge(polozka);
            if (kniha_at_polozkaOld != null && !kniha_at_polozkaOld.equals(kniha_at_polozkaNew)) {
                kniha_at_polozkaOld.getPolozky().remove(polozka);
                kniha_at_polozkaOld = em.merge(kniha_at_polozkaOld);
            }
            if (kniha_at_polozkaNew != null && !kniha_at_polozkaNew.equals(kniha_at_polozkaOld)) {
                kniha_at_polozkaNew.getPolozky().add(polozka);
                kniha_at_polozkaNew = em.merge(kniha_at_polozkaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = polozka.getId();
                if (findpolozka(id) == null) {
                    throw new NonexistentEntityException("The polozka with id " + id + " no longer exists.");
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
            polozka polozka;
            try {
                polozka = em.getReference(polozka.class, id);
                polozka.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The polozka with id " + id + " no longer exists.", enfe);
            }
            Kniha kniha_at_polozka = polozka.getKniha_at_polozka();
            if (kniha_at_polozka != null) {
                kniha_at_polozka.getPolozky().remove(polozka);
                kniha_at_polozka = em.merge(kniha_at_polozka);
            }
            em.remove(polozka);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<polozka> findpolozkaEntities() {
        return findpolozkaEntities(true, -1, -1);
    }

    public List<polozka> findpolozkaEntities(int maxResults, int firstResult) {
        return findpolozkaEntities(false, maxResults, firstResult);
    }

    private List<polozka> findpolozkaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(polozka.class));
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

    public polozka findpolozka(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(polozka.class, id);
        } finally {
            em.close();
        }
    }

    public int getpolozkaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<polozka> rt = cq.from(polozka.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
