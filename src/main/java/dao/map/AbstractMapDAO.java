package dao.map;

import beans.Identity;
import dao.CommonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractMapDAO <T extends Identity> implements CommonDao<T> {//not static
    private static final Logger log = LoggerFactory.getLogger(AbstractMapDAO.class);
    private AtomicLong id = new AtomicLong();
    private TreeMap<Long,T> map;

    AbstractMapDAO(TreeMap<Long, T> map){
        this.map = map;
    }

    private TreeMap<Long,T> getMap(){
        return this.map;
    }

    private long incId(){
        this.id.incrementAndGet();
        return this.id.get();
    }

    @Override
    public T add(T identity) {
        log.info("Adding new entity of type {}, entity is {}", identity.getClass(), identity);
        long id = incId();
        identity.setId(id);
        getMap().put(id,identity);
        log.info("Entity of type {} successfully added, id is {}", identity.getClass(), id);
        return identity;
    }

    @Override
    public T update(T identity) {
        log.info("Updating map entity of type {} with id = {}",identity.getClass(),identity.getId());
        getMap().put(identity.getId(),identity);
        log.info("Updating map entity with id = {} successfully finished",identity.getId());
        return identity;
    }

    @Override
    public T getById(Long id) {
        log.info("Getting map entity by id {}",id);
        return getMap().get(id);
    }

    @Override
    public List<T> getAll() {
        log.info("Getting all map entities");
        List<T> returns = new LinkedList<>();
        for (Map.Entry<Long, T> entry :
                getMap().entrySet()) {
            returns.add(entry.getValue());
        }
        log.info("All map entities were successfully gotten");
        return returns;
    }

    @Override
    public void delete(Identity identity) {
        log.info("Deleting map entity of type {}, entity {}",identity.getClass(),identity);
        getMap().remove(identity.getId());
        log.info("Deleting map entity {} successfully finished",identity);
    }
}
