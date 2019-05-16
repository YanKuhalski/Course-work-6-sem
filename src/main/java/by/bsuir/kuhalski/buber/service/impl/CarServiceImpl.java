package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Car;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.repository.Repository;
import by.bsuir.kuhalski.buber.repository.specification.Specification;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByDriverIDAsDriver;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByDriverId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl extends AbstractService<Car> implements CarService {
    @Autowired
    private UserService userService;

    protected CarServiceImpl(@Qualifier("carRepository") Repository repository) {
        this.repository = repository;
    }

    @Override
    public void addCar(User user, String brand, String model, String number) {
        Car car = new Car();
        car.setDriver(user);
        car.setBrandName(brand);
        car.setCarModel(model);
        car.setCarNumber(number);

        repository.save(car);
    }

    @Override
    public List<Car> loadAvailableCars() {
        List<User> emptyDrivers = userService.findAllEmptyDrivers();
        List<Car> availableCars = new ArrayList<>();
        for (User driver : emptyDrivers) {
            Optional<Car> carForDriver = findCarForDriver(driver);
            if (carForDriver.isPresent()){
                availableCars.add(carForDriver.get());
            }
        }
        return availableCars;
    }

    @Override
    public Optional<Car> findCarForDriver(User user) {
        Specification specification = new SpecificationByDriverIDAsDriver(user.getId());
        return repository.queryForSingleResult(specification);
    }
}
