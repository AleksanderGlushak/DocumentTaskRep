package dao.database;

import beans.Identity;
import dao.CommonDao;
import dao.map.AbstractMapDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

//service layer, not connected DAOs(independent)
public class CommonDatabaseDAO<T extends Identity> implements CommonDao<T>, Iterator<T> {
    private Long currentId;
    private Long nextId;
    private static final Logger log = LoggerFactory.getLogger(CommonDatabaseDAO.class);
    private static final EntityManager em = Persistence.createEntityManagerFactory("Exadel").createEntityManager();
    private Class<T> type;

    public CommonDatabaseDAO(Class<T> type) {
        this.type = type;
    }


    @Override
    public T add(T t) {
        log.info("Adding new entity of type {}, entity is {}", t.getClass(), t);
        try{
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            log.info("Adding new entity {} successfully finished, it\'s id = {}", t, t.getId());
        } catch (IllegalStateException e){
            log.error("Exception during adding -> rolling back");
            em.getTransaction().rollback();
            throw new IllegalStateException();
        }
        log.info("Adding new entity {} successfully finished, it\'s id = {}", t, t.getId());

        return t;
    }

    @Override
    public T update(T t) {
        log.info("Updating entity of type {}, entity is {}", t.getClass(), t);
        T ret = null;
        em.getTransaction().begin();
        ret = em.merge(t);
        em.getTransaction().commit();
        log.info("Updating successfully finished");
        return ret;
    }

    @Override
    public T getById(Long id) {
        return (T) em.find(type,id);
    }

    @Override
    public List<T> getAll() {
        log.info("Getting all entities of type {}", type);
        List<T> l = em.createQuery("select t from "+ type.getName() + " t").getResultList();
        log.info("Getting of entities successfully finished");
        return l;
    }

    @Override
    public void delete(T o) {
        log.info("Deleting of entity of type {}, entity if {}",o.getClass(),o);
        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();
        log.info("Deleting of entity {} successfully finished",o);
    }

    @Override
    public boolean hasNext() {
        Long id = this.currentId;
        if(id == null){
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = builder.createQuery( Long.class );
            Root<T> personRoot = criteria.from( type );
            criteria.select( builder.min(personRoot.<Long>get("id")));
            id = em.createQuery(criteria).getSingleResult();
        } else {
            if(this.nextId != null & this.nextId > id)
                return true;
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = builder.createQuery( Long.class );
            Root<T> personRoot = criteria.from( type );
            criteria.select( builder.min(personRoot.<Long>get("id"))).where(builder.gt(personRoot.<Long>get("id"), id));
            id = em.createQuery( criteria ).getSingleResult();
        }
        this.nextId = id;
        if(id != null){
            return true;
        } else
            return false;
    }

    @Override
    public T next() {
        T ret = null;
        if(hasNext()){
            currentId = nextId;
            ret = getById(this.currentId);
        } else
            throw new NoSuchElementException();
        return ret;
    }
}
