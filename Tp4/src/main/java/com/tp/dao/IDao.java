package com.tp.dao;

import java.util.List;

public interface IDao<T> {
    boolean create(T t);
    boolean update(T t);
    boolean delete(T t);
    T findById(int id);
    List<T> findAll();
}
