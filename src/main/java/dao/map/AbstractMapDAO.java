package dao.map;

import beans.Identity;
import dao.AbstractDAO;

import java.util.*;

public abstract class AbstractMapDAO <T extends Identity> implements AbstractDAO<T> {
    TreeMap<Long,T> map = new TreeMap<>();
    private long id = 0;
    private Long current_id;
    private void incId(){
        this.id++;
    }

    @Override
    public T readFirst() {
        if(this.map.isEmpty())
            return null;
        current_id = map.firstEntry().getKey();
        return map.firstEntry().getValue();
    }

    @Override
    public T readNext() {
        if (this.current_id == null)
            return readFirst();
        this.current_id = map.higherKey(current_id);
        if (this.current_id == null)
            return null;
        return this.map.get(this.current_id);
    }

    @Override
    public T add(T identity) {
        map.put(id,identity);
        identity.setId(id);
        incId();
        return identity;
    }

    @Override
    public T update(T identity) {
        map.put(identity.getId(),identity);
        return identity;
    }

    @Override
    public T getById(Long id) {
        return map.get(id);
    }

    @Override
    public List<T> getAll() {
        List<T> returns = new LinkedList<>();
        for (Map.Entry<Long, T> entry :
                map.entrySet()) {
            returns.add(entry.getValue());
        }
        return returns;
    }

    @Override
    public void delete(Identity identity) {
        map.remove(identity.getId());
    }
}
