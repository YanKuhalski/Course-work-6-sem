package by.bsuir.kuhalski.buber.controller;

import by.bsuir.kuhalski.buber.model.*;
import by.bsuir.kuhalski.buber.service.impl.CarService;
import by.bsuir.kuhalski.buber.service.impl.RegionService;
import by.bsuir.kuhalski.buber.service.impl.TripService;
import by.bsuir.kuhalski.buber.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ClientController {
    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private CarService carService;

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping(value = "/common/addRideRequest")
    public String addRideRequest(@RequestParam(value = "regionIdfrom") long from,
                                 @RequestParam(value = "regionIdto") long to,
                                 @RequestParam(value = "car") long carId) {
        Car car = carService.loadEntityById(carId).get();
        User carDriver = car.getDriver();
        boolean isEmpty = tripService.driverIsEmpty(carDriver);

        if (isEmpty) {
            Trip trip = new Trip();
            User client = userService.findByUserName(getUserName()).get();
            trip.setClient(client);
            trip.setDriver(car.getDriver());
            trip.setCar(car);
            List<Discount> discounts = new ArrayList<>(client.getDiscounts());
            trip.setDiscount(discounts.get(0));
            Optional<Region> optionalFrom = regionService.loadEntityById(from);
            Optional<Region> optionalTo = regionService.loadEntityById(to);
            trip.setStartRegion(optionalFrom.get());
            trip.setEndRegion(optionalTo.get());
            trip.setAccepted(false);
            trip.setPayed(false);
            trip.setFinished(false);
            tripService.save(trip);
            return "redirect:/";
        } else {
            return "redirect:/?driverNotEmpty";
        }
    }

    @RequestMapping(value = "/common/cancelTrip/{tripId}")
    public String cancelTrip(@PathVariable long tripId) {
        Optional<Trip> optionalTrip = tripService.loadEntityById(tripId);
        if (optionalTrip.isPresent()){
            Trip trip = optionalTrip.get();
            boolean accepted = trip.isAccepted();
            if(!accepted){
                trip.setFinished(true);
                tripService.save(trip);

            }else {
                return "redirect:/?cantCancel";
            }
        }
        return "redirect:/";
    }

    private String getUserName() {
        return getAuthentication().getName();
    }

}
