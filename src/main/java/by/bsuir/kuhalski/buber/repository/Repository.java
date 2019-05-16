package by.bsuir.kuhalski.buber.repository;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void save(T item);

    void deleteById(long id);

    void delete(T item);

    Optional<T> queryById(long id);

    Optional<T> queryForSingleResult(Specification specification);

    List<T> queryBySpecificationWithLimit(Specification specification, long limit, long offset);

    List<T> queryWithLimit(long limit, long offset);

    List<T> query(Specification specification);

    List<T> queryAll();

    long countAllRows();

    long countRows(Specification specification);
}
