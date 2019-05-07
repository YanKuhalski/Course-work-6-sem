package by.bsuir.kuhalski.buber.repository.impl;

import by.bsuir.kuhalski.buber.model.Region;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RegionRepository extends AbstractRepository<Region> {
    private static final String DATABASE_NAME = " region ";

    @Override
    protected String getBaseName() {
        return DATABASE_NAME;
    }

    @Override
    public List<Region> queryAll() {
        return entityManager.createNamedQuery("queryAllRegions").getResultList();
    }
}
