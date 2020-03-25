package services;

import beans.Identity;
import dao.CommonDao;

import java.util.List;

public abstract class  AbstractService <T extends Identity> {
    protected CommonDao<T> dao;

    public void delete(T t){
        dao.delete(t);
    }
    public void add(T t){
        dao.add(t);
    }
    public void update(T t){
        dao.update(t);
    }
    public T getById(Long id){
        return dao.getById(id);
    }
    public List<T> getAll(){
        return dao.getAll();
    }
}
