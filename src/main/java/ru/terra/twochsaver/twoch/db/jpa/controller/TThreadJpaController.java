/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.terra.twochsaver.twoch.db.jpa.controller;

import ru.terra.twochsaver.twoch.db.jpa.TThread;
import ru.terra.twochsaver.twoch.db.jpa.controller.exceptions.NonexistentEntityException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author terranz
 */
public class TThreadJpaController implements Serializable {

    public TThreadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TThread TThread) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TThread);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TThread TThread) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TThread = em.merge(TThread);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TThread.getId();
                if (findTThread(id) == null) {
                    throw new NonexistentEntityException("The tThread with id " + id + " no longer exists.");
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
            TThread TThread;
            try {
                TThread = em.getReference(TThread.class, id);
                TThread.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TThread with id " + id + " no longer exists.", enfe);
            }
            em.remove(TThread);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TThread> findTThreadEntities() {
        return findTThreadEntities(true, -1, -1);
    }

    public List<TThread> findTThreadEntities(int maxResults, int firstResult) {
        return findTThreadEntities(false, maxResults, firstResult);
    }

    private List<TThread> findTThreadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TThread.class));
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

    public TThread findTThread(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TThread.class, id);
        } finally {
            em.close();
        }
    }

    public int getTThreadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TThread> rt = cq.from(TThread.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public TThread findByStartMessage(Long id) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("TThread.findByStartMessage").setParameter("startMessage", id);
            return (TThread) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
