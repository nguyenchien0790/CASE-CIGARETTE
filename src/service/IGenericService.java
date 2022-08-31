package service;

import java.util.List;

public interface IGenericService<T> {

    List<T> findAll();

    void save(T t);

    void remove(int id);

    T findById(int id);

    void updateData();

    int getLastId();

    void edit(T t);

    T findByName(String name);
}
