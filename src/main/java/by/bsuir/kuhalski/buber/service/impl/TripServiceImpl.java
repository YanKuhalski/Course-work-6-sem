package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.model.Trip;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.repository.Repository;
import by.bsuir.kuhalski.buber.repository.specification.Specification;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByDriverIdAcceptionAndEndOfTrip;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByDriverId;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByClientId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl extends AbstractService<Trip> implements TripService {
    protected TripServiceImpl(@Qualifier("tripRepository") Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Trip> findTripByUser(User user) {
        List<Role> roles = new ArrayList<>(user.getRoles());
        Role role = roles.get(0);
        Specification specification = null;

        if (role.getRole().equals("ROLE_DRIVER")) {
            specification = new SpecificationByDriverId(user.getId());
        }
        if (role.getRole().equals("ROLE_USER")) {
            specification = new SpecificationByClientId(user.getId());
        }
        List<Trip> trips = new ArrayList<>();
        if (specification != null) {
            trips.addAll(repository.query(specification));
        }
        return trips;
    }

    @Override
    public boolean driverIsEmpty(User carDriver) {
        return driverIsEmpty(carDriver.getId());
    }

    @Override
    public boolean driverIsEmpty(long carDriverId) {
        Specification specification = new SpecificationByDriverIdAcceptionAndEndOfTrip(carDriverId, true, false);
        return !repository.queryForSingleResult(specification).isPresent();
    }
}
