package by.bsuir.kuhalski.buber.controller;

import by.bsuir.kuhalski.buber.model.Trip;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.impl.TripService;
import by.bsuir.kuhalski.buber.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class StatisticController {
    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/common/tripStatistic/{userName}")
    private ModelAndView showTripStatistic(@PathVariable String userName) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> optionalUser = userService.findByUserName(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Trip> trips = tripService.findTripByUser(user);
            modelAndView.addObject("trips", trips);
        }

        modelAndView.setViewName("common/tripStatisticPage");
        return modelAndView;
    }
}
