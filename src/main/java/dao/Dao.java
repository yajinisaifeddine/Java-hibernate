package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(Long id);
    List<T> findAll();

    Optional<T> findBy(String filed,String value);
    List<T> findMultipleBy(String filed,String value);

    Optional<T> save(T entity);
    Optional<T> update(T entity);

    Optional<T> delete(T entity);
    Optional<T> delete(Long id);

}
