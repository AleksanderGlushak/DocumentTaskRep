package dao.database;

import beans.Identity;
import beans.User;
import dao.AbstractDAO;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public abstract class AbstractDatabaseDAO<T extends Identity> implements AbstractDAO<T> {
    private static EntityManager em = Persistence.createEntityManagerFactory("Exadel").createEntityManager();
    protected Class type;
    @Override
    public T add(T t) {
        T ret = null;
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return ret;
    }

    @Override
    public T update(T t) {
        T ret = null;
        em.getTransaction().begin();
        ret = em.merge(t);
        em.getTransaction().commit();
        return ret;
    }

    @Override
    public T getById(Long id) {
        return (T) em.find(type,id);
    }

    @Override
    public List<T> getAll() {
        return em.createQuery("select t from "+ type.getName() + " t").getResultList();
    }

    @Override
    public void delete(T o) {
        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();
    }
}
