/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.terra.twochsaver.twoch.db.jpa.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ru.terra.twochsaver.twoch.db.jpa.TMessage;
import ru.terra.twochsaver.twoch.db.jpa.controller.exceptions.NonexistentEntityException;
import ru.terra.twochsaver.twoch.db.jpa.controller.exceptions.PreexistingEntityException;

/**
 * 
 * @author terranz
 */
public class TMessageJpaController implements Serializable {

	public TMessageJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(TMessage TMessage) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(TMessage);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTMessage(TMessage.getNum()) != null) {
				throw new PreexistingEntityException("TMessage " + TMessage + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(TMessage TMessage) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			TMessage = em.merge(TMessage);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = TMessage.getNum();
				if (findTMessage(id) == null) {
					throw new NonexistentEntityException("The tMessage with id " + id + " no longer exists.");
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
			TMessage TMessage;
			try {
				TMessage = em.getReference(TMessage.class, id);
				TMessage.getNum();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The TMessage with id " + id + " no longer exists.", enfe);
			}
			em.remove(TMessage);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<TMessage> findTMessageEntities() {
		return findTMessageEntities(true, -1, -1);
	}

	public List<TMessage> findTMessageEntities(int maxResults, int firstResult) {
		return findTMessageEntities(false, maxResults, firstResult);
	}

	private List<TMessage> findTMessageEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(TMessage.class));
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

	public TMessage findTMessage(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(TMessage.class, id);
		} finally {
			em.close();
		}
	}

	public int getTMessageCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<TMessage> rt = cq.from(TMessage.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public boolean isMessageExists(Long num) {
		return findTMessage(num) != null;
	}

}
