package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Car;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.Service;

import java.util.List;
import java.util.Optional;

public interface CarService extends Service<Car> {
    void addCar(User user, String brand, String model, String number);

    List<Car> loadAvailableCars();

    Optional<Car> findCarForDriver(User user);
}
