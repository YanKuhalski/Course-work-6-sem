package by.bsuir.kuhalski.buber.service.impl;


import by.bsuir.kuhalski.buber.model.Trip;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.Service;

import java.util.List;

public interface TripService extends Service<Trip> {
    List<Trip> findTripByUser(User user);

    boolean driverIsEmpty(User carDriver);
    boolean driverIsEmpty(long carDriverId);
}
