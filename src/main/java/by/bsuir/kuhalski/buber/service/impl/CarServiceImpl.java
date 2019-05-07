package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Car;
import by.bsuir.kuhalski.buber.repository.Repository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends AbstractService<Car> implements CarService {
    protected CarServiceImpl(@Qualifier("carRepository") Repository repository) {
        this.repository = repository;
    }
}
