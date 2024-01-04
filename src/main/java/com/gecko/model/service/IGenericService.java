package com.gecko.model.service;

import java.util.List;

public interface IGenericService<T,ID>{
    List<T> findAll(ID id);
    boolean create(T t);
    boolean update(T t);
    boolean delete(ID id);
    T findById(ID id);
    List<T> findByName(String str);
}
