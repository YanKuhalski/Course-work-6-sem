package by.bsuir.kuhalski.buber.controller;

import by.bsuir.kuhalski.buber.model.Trip;
import by.bsuir.kuhalski.buber.service.impl.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class DriverController {
    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/driver/acceptTrip/{tripId}")
    public String acceptTrip(@PathVariable long tripId) {
        Optional<Trip> optional = tripService.loadEntityById(tripId);
        if (optional.isPresent()){
            Trip trip = optional.get();
            if(!trip.isFinished()){
                trip.setAccepted(true);
                tripService.save(trip);
            }
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/driver/acceptTripPayment/{tripId}")
    public String acceptTripPayment(@PathVariable long tripId) {
        Optional<Trip> optional = tripService.loadEntityById(tripId);
        if (optional.isPresent()){
            Trip trip = optional.get();
            if(!trip.isFinished()){
                trip.setPayed(true);
                tripService.save(trip);
            }
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/driver/acceptTripFinish/{tripId}")
    public String  finishTrip(@PathVariable long tripId) {
        Optional<Trip> optional = tripService.loadEntityById(tripId);
        if (optional.isPresent()){
            Trip trip = optional.get();
            if(!trip.isFinished()){
                trip.setFinished(true);
                tripService.save(trip);
            }
        }
        return "redirect:/";
    }
}
