package dao;

import java.util.List;

public interface DAOInterface<T> {
    public void create(T obj);
    public List<T> getAll();
}
