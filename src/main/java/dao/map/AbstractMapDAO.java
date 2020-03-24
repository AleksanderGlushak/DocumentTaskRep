package dao.map;

import beans.Identity;
import dao.CommonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractMapDAO <T extends Identity> implements CommonDao<T> {//not static
    private static final Logger log = LoggerFactory.getLogger(AbstractMapDAO.class);
    protected abstract TreeMap<Long,T> getMap();
    private AtomicLong id = new AtomicLong();
    private Long currentId;
    private long incId(){
        this.id.incrementAndGet();
        return this.id.get();
    }

    @Override
    public T readFirst() {
        if(getMap().isEmpty())
            return null;
        currentId = getMap().firstEntry().getKey();
        return getMap().firstEntry().getValue();
    }

    @Override
    public T readNext() {
        if (this.currentId == null)
            return readFirst();
        this.currentId = getMap().higherKey(currentId);
        if (this.currentId == null)
            return null;
        return getMap().get(this.currentId);
    }

    @Override
    public T add(T identity) {
        log.info("Adding new entity of type {}, entity is {}", identity.getClass(), identity);
        long id = incId();
//        incId();
        identity.setId(id);
        getMap().put(id,identity);
        log.info("Entity of type {} successfully added, id is {}", identity.getClass(), id);
        return identity;
    }

    @Override
    public T update(T identity) {
        getMap().put(identity.getId(),identity);
        return identity;
    }

    @Override
    public T getById(Long id) {
        return getMap().get(id);
    }

    @Override
    public List<T> getAll() {
        List<T> returns = new LinkedList<>();
        for (Map.Entry<Long, T> entry :
                getMap().entrySet()) {
            returns.add(entry.getValue());
        }
        return returns;
    }

    @Override
    public void delete(Identity identity) {
        getMap().remove(identity.getId());
    }
}
