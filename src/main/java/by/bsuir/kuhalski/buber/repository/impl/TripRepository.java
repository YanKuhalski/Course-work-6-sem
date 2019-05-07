package by.bsuir.kuhalski.buber.repository.impl;

import by.bsuir.kuhalski.buber.model.Trip;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TripRepository extends AbstractRepository<Trip> {
    private static final String DATABASE_NAME = " trip ";

    @Override
    protected String getBaseName() {
        return DATABASE_NAME;
    }

    @Override
    public List<Trip> queryAll() {
        return entityManager.createNamedQuery("queryAllTrips").getResultList();
    }
}
