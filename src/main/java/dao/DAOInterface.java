package dao;

import java.util.List;

public interface DAOInterface<T> {
    public void create(T obj);
    public void update(T obj);
    public T getById(Integer id);
    public List<T> getAll();
}
