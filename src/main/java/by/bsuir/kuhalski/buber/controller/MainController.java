package by.bsuir.kuhalski.buber.controller;

import by.bsuir.kuhalski.buber.logic.Calculator;
import by.bsuir.kuhalski.buber.model.Trip;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.impl.CarService;
import by.bsuir.kuhalski.buber.service.impl.RegionService;
import by.bsuir.kuhalski.buber.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private Calculator calculator;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage(@RequestParam(value = "cantCancel", required = false) String cantCancel,
                                    @RequestParam(value = "driverNotEmpty", required = false) String driverNotEmpty) {
        ModelAndView model = new ModelAndView();

        if (!getUserName().equals("anonymousUser")) {
            if (getRole().equals("[ROLE_USER]")) {
                Optional<User> optionalUser = userService.findByUserName(getUserName());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    boolean haveActiveRides = false;
                    List<Trip> tripsAsClient = user.getTripsAsClient();

                    for (Trip trip : tripsAsClient) {
                        if (!trip.isFinished()) {
                            haveActiveRides = true;
                            model.addObject("activeTrip", trip);
                            double price = calculator.calculateCost(trip);

                            model.addObject("price", price);
                            if (cantCancel != null) {
                                model.addObject("cantCancel", "Cant cancel trip");
                            }
                        }
                    }
                    if (!haveActiveRides) {
                        if (driverNotEmpty != null) {
                            model.addObject("driverNotEmpty", "Driver that u choose is not empty");
                        }
                        model.addObject("regions", regionService.loadAllEntities());
                        model.addObject("cars", carService.loadAvailableCars());

                    }
                }
            }
            if (getRole().equals("[ROLE_DRIVER]")) {
                Optional<User> optionalUser = userService.findByUserName(getUserName());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    boolean haveActiveRides = false;
                    List<Trip> tripsAsDriver = user.getTripsAsDriver();

                    for (Trip trip : tripsAsDriver) {
                        if (!trip.isFinished() && trip.isAccepted()) {
                            haveActiveRides = true;
                            model.addObject("activeTrip", trip);
                            double price = calculator.calculateCost(trip);
                            double profit = calculator.calculateProfit(trip);
                            model.addObject("price", price);
                            model.addObject("profit", profit);
                        }
                    }
                    if (!haveActiveRides) {
                        List<Trip> activeTrips = new ArrayList<>();
                        List<Double>profits = new ArrayList<>();
                        for (Trip trip : tripsAsDriver) {
                            if (!trip.isFinished()) {
                                activeTrips.add(trip);
                                profits.add(calculator.calculateProfit(trip));
                            }
                        }
                        model.addObject("profits", profits);
                        model.addObject("size", profits.size());
                        model.addObject("activeTrips", activeTrips);
                    }
                }
            }

        }

        model.setViewName("welcomePage");
        setUserNameIfExists(model);
        return model;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }
        if (logout != null) {
            model.addObject("message", "Log out successfully.");
        }
        model.setViewName("loginPage");
        return model;
    }


    private void setUserNameIfExists(ModelAndView modelAndView) {
        String userName = getUserName();
        if (!userName.equals("anonymousUser")) {
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("role", getRole());
        }
    }

    private String getUserName() {
        return getAuthentication().getName();
    }

    private String getRole() {
        return getAuthentication().getAuthorities().toString();
    }
}
