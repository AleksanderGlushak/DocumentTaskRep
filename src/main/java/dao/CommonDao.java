package dao;

import beans.Annotation;
import beans.Identity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface CommonDao<T extends Identity> {
    T add(T t) ;
    T update(T t);
    T getById(Long id);
    List<T> getAll();
    void delete(T t);
    T readNext();
    T readFirst();
}
