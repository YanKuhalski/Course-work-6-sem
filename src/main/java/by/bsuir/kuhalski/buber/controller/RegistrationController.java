package by.bsuir.kuhalski.buber.controller;

import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.impl.CarService;
import by.bsuir.kuhalski.buber.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/registrationPage/{role}")
    public ModelAndView openRegistrationPage(@PathVariable String role, @RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        if (error != null) {
            modelAndView.addObject("error", "User with such login is already exists");
        }
        modelAndView.setViewName("registrationPage");
        return modelAndView;
    }

    @RequestMapping(value = "/register/{role}")
    private String registerNewUser(@PathVariable String role,
                                   @RequestParam(value = "username") String login,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "brand", required = false) String brand,
                                   @RequestParam(value = "model", required = false) String model,
                                   @RequestParam(value = "number", required = false) String number) {
        Optional<User> optionalUser = userService.findByUserName(login);
        if (!optionalUser.isPresent()) {
            if (role.equals("ROLE_DRIVER")) {
                if (brand != null && model != null && number != null) {
                    userService.addUser(role, login, password);
                    Optional<User> newUserOptional = userService.findByUserName(login);
                    carService.addCar(newUserOptional.get(), brand, model, number);
                }
                else {
                    return "redirect:/registrationPage/" + role + "?error";
                }
            } else {
                userService.addUser(role, login, password);
            }
            return "redirect:/";
        } else {
            return "redirect:/registrationPage/" + role + "?error";
        }
    }


}
