package dao;

import beans.Annotation;
import beans.Identity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface AbstractDAO <T extends Identity> {
    public T add(T t);
    public T update(T t);
    public T getById(Long id);
    public List<T> getAll();
    public void delete(T t);
}
