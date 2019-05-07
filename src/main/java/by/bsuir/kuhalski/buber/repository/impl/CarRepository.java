package by.bsuir.kuhalski.buber.repository.impl;

import by.bsuir.kuhalski.buber.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository extends  AbstractRepository<Car> {
    private static final String DATABASE_NAME = " car ";

    @Override
    protected String getBaseName() {
        return DATABASE_NAME;
    }

    @Override
    public List<Car> queryAll() {
        return entityManager.createNamedQuery("queryAllCars").getResultList();
    }
}
