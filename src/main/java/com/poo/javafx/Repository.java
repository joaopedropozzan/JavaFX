package com.poo.javafx;

import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Repository<T extends Model<T>> {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("cocarPU");
    private final Class<T> clazz;

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public ArrayList<T> objetos() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(this.clazz);
            Root<T> root = cq.from(this.clazz);
            cq.select(root);
            return new ArrayList<>(em.createQuery(cq).getResultList());
        } finally {
            em.close();
        }
    }

    public T comID(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            em.close();
        }
    }

    public void adicionar(T objeto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new IllegalArgumentException("Registro já cadastrado no sistema.", e);
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new IllegalArgumentException("Erro ao salvar: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public boolean atualizar(int id, T objetoAtualizado) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T existente = em.find(clazz, id);
            if (existente == null) {
                em.getTransaction().rollback();
                return false;
            }

            objetoAtualizado.herdarDe(existente);
            objetoAtualizado.setID(id);
            em.merge(objetoAtualizado);
            em.getTransaction().commit();
            return true;
        } catch (RollbackException e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new IllegalArgumentException("Registro já cadastrado no sistema.", e);
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new IllegalArgumentException("Erro ao atualizar: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void remover(T objetoRemover) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T existente = em.find(clazz, objetoRemover.getID());
            if (existente != null) {
                em.remove(existente);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new IllegalArgumentException("Erro ao remover: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
