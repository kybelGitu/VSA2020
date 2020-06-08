/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3_a_vsa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pkg3_a_vsa.exceptions.NonexistentEntityException;
import pkg3_a_vsa.exceptions.PreexistingEntityException;

/**
 *
 * @author vsa
 */
public class KnihaJpaController implements Serializable {

    public KnihaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kniha kniha) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kniha);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKniha(kniha.getIsbn()) != null) {
                throw new PreexistingEntityException("Kniha " + kniha + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kniha kniha) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kniha = em.merge(kniha);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = kniha.getIsbn();
                if (findKniha(id) == null) {
                    throw new NonexistentEntityException("The kniha with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kniha kniha;
            try {
                kniha = em.getReference(Kniha.class, id);
                kniha.getIsbn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kniha with id " + id + " no longer exists.", enfe);
            }
            em.remove(kniha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kniha> findKnihaEntities() {
        return findKnihaEntities(true, -1, -1);
    }

    public List<Kniha> findKnihaEntities(int maxResults, int firstResult) {
        return findKnihaEntities(false, maxResults, firstResult);
    }

    private List<Kniha> findKnihaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kniha.class));
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

    public Kniha findKniha(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kniha.class, id);
        } finally {
            em.close();
        }
    }

    public int getKnihaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kniha> rt = cq.from(Kniha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
