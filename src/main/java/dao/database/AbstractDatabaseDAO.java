package dao.database;

import beans.Identity;
import beans.User;
import dao.AbstractDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDatabaseDAO<T extends Identity> implements AbstractDAO<T> {
    private Long currentId;
    private static EntityManager em = Persistence.createEntityManagerFactory("Exadel").createEntityManager();
    protected Class type;

    @Override
    public T readFirst() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery( Long.class );
        Root<T> personRoot = criteria.from( type );
        criteria.select( builder.min(personRoot.<Long>get("id")));
        this.currentId = em.createQuery( criteria ).getSingleResult();
        if(currentId != null){
            return getById(this.currentId);
        } else
            return null;
    }

    @Override
    public T readNext() {
        if(this.currentId == null){
             return readFirst();
        } else {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = builder.createQuery( Long.class );
            Root<T> personRoot = criteria.from( type );
            criteria.select( builder.min(personRoot.<Long>get("id"))).where(builder.gt(personRoot.<Long>get("id"), this.currentId));
            this.currentId = em.createQuery( criteria ).getSingleResult();
        }
        if (this.currentId != null)
            return getById(this.currentId);
        else
            return null;
    }

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
