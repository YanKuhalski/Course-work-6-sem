package by.bsuir.kuhalski.buber.service;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    void save(T entity);

    Optional<T> loadEntityById(long id);

    List<T> loadEntitiesWithLimit(long limit, long offset);

    List<T> loadEntitiesWithLimitAndSpecification(Specification specification, long limit, long offset);

    List<T> loadAllEntities();

    boolean deleteById(long id);

    long countAllRows();
}
