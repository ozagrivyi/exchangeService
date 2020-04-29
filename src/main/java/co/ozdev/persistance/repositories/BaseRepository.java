package co.ozdev.persistance.repositories;

import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    List<T> findAll();
    T save(T entity);
}
