package com.darkwhiskers.daos;

import java.util.List;

public interface GenericDAO <T, ID> {
    
    void create(T entity);
    List<T> findAll();
    T findById(ID id);
    void update(T entity);
    void delete(ID id);

}
