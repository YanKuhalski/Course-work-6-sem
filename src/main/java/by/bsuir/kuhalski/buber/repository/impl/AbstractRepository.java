package by.bsuir.kuhalski.buber.repository.impl;


import by.bsuir.kuhalski.buber.repository.Repository;
import by.bsuir.kuhalski.buber.repository.specification.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@org.springframework.stereotype.Repository
public abstract class AbstractRepository<T> implements Repository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private static final String COUNT_ROW_QUERY = "SELECT COUNT(id) FROM ";
    private static final String SELECT_SQL = " SELECT * FROM  ";
    private static final String LIMIT = " LIMIT :limit OFFSET :offset ";

    @Override
    public void save(T item) {
        entityManager.merge(item);
    }

    @Override
    public void deleteById(long id) {
       /* Optional<T> objectOptById = queryById(id);
        if (objectOptById.isPresent()) {
            T objectById = objectOptById.get();
            delete(objectById);
        }*/
        entityManager.createQuery("DELETE FROM "+getBaseName()+" WHERE id = :id").setParameter("id", id).executeUpdate();
    }
    @Override
    public void delete(T item) {
        entityManager.remove(entityManager.contains(item) ? item : entityManager.merge(item));
    }

    @Override
    public Optional<T> queryById(long id) {
        Class<T> classType = getClassType();
        T result = entityManager.find(classType, id);

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<T> queryForSingleResult(Specification specification) {
        List<T> listOfEntity = query(specification);
        return listOfEntity.isEmpty() ? Optional.empty() : Optional.of(listOfEntity.get(0));
    }

    @Override
    public List<T> query(Specification specification) {
        String sql = SELECT_SQL + getBaseName() + specification.toSql();

        Query query = entityManager.createNativeQuery(sql, getClassType());
        Map<String, Object> parameterMap = specification.getParameterMap();
        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }
        return (List<T>) query.getResultList();
    }

    @Override
    public List<T> queryBySpecificationWithLimit(Specification specification, long limit, long offset) {
        String sql = SELECT_SQL + getBaseName() + specification.toSql() + LIMIT;

        Query query = entityManager.createNativeQuery(sql, getClassType());
        Map<String, Object> parameterMap = specification.getParameterMap();
        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);

        return (List<T>) query.getResultList();
    }

    @Override
    public List<T> queryWithLimit( long limit, long offset) {
        String sql = SELECT_SQL + getBaseName() + LIMIT;

        Query query = entityManager.createNativeQuery(sql, getClassType());

        query.setParameter("limit", limit);
        query.setParameter("offset", offset);

        return (List<T>) query.getResultList();
    }

    @Override
    public long countRows(Specification specification) {
        Query query = entityManager.createQuery(COUNT_ROW_QUERY + getClassType().getSimpleName() + specification.toSql());
        Map<String, Object> parameterMap = specification.getParameterMap();
        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }
        return (long) query.getSingleResult();
    }

    @Override
    public long countAllRows() {
        Query query = entityManager.createQuery(COUNT_ROW_QUERY + getClassType().getSimpleName() );
        return (long) query.getSingleResult();
    }

    protected Class<T> getClassType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    protected abstract String getBaseName();
}
