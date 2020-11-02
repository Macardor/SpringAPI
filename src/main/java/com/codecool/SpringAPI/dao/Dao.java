package com.codecool.SpringAPI.dao;

import java.util.List;

public interface Dao<T> {

    abstract List<T> getAll();
    abstract T get(long id);
    abstract void add(T t);
    abstract void update(T t);
    abstract void delete(long id);

}
