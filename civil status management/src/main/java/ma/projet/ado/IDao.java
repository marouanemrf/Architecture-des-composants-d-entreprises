package ma.projet.ado;

import java.util.List;

public interface IDao<T> {
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    T findById(int id);
    List<T> findAll();
}
