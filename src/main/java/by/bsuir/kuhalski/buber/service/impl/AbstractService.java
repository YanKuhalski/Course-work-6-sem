package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.repository.Repository;
import by.bsuir.kuhalski.buber.repository.specification.Specification;
import by.bsuir.kuhalski.buber.service.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Setter
@Getter
public abstract class AbstractService<T> implements Service<T> {
    private static final String RESULT_OF_SEARCHING_MESSAGE = "The result of searching ";
    private static final String LOADING_RESULT_MESSAGE = " entity was loaded ";
    private static final String SAVING_MASSAGE = "Saving of entity was successful | entity = ";
    private static final String DELETE_MESSAGE = "Delete by id was successful | id = ";
    private static final String FAILED_DELETE_MESSAGE = "Delete by id is failed " +
            "cause entity with such id is not exists | id = ";
    protected final Logger log;
    protected Repository repository;


    protected AbstractService( ) {
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    @Transactional
    public void save(T entity) {
        repository.save(entity);
        log.info(SAVING_MASSAGE + entity);
    }

    @Override
    @Transactional
    public Optional<T> loadEntityById(long id) {
        Optional optional = repository.queryById(id);
        log.info(RESULT_OF_SEARCHING_MESSAGE + " is " + optional.isPresent());
        return optional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> loadAllEntities() {
        List entityList = repository.queryAll();
        log.info(entityList.size() + LOADING_RESULT_MESSAGE);
        return entityList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> loadEntitiesWithLimitAndSpecification(Specification specification, long limit, long offset) {
        return repository.queryBySpecificationWithLimit(specification, limit, offset);
    }

    @Override
    public List<T> loadEntitiesWithLimit(long limit, long offset) {
        return repository.queryWithLimit(limit, offset);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        Optional optional = repository.queryById(id);
        if (optional.isPresent()) {
            Object itemToDelete = optional.get();
            repository.delete(itemToDelete);
            log.info(DELETE_MESSAGE + id);
            return true;
        } else {
            log.info(FAILED_DELETE_MESSAGE + id);
            return false;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public long countAllRows() {
        return repository.countAllRows();
    }


    @Transactional(readOnly = true)
    protected List<T> loadEntities(Specification specification) {
        List query = repository.query(specification);
        log.info(RESULT_OF_SEARCHING_MESSAGE + " is " + query.size() + " entity");
        return query;
    }

    @Transactional
    protected Optional<T> loadSingleEntity(Specification specification) {
        return repository.queryForSingleResult(specification);
    }

    @Transactional(readOnly = true)
    protected long countRows(Specification specification) {
        return repository.countRows(specification);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }
}
