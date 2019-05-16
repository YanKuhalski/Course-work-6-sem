package by.bsuir.kuhalski.buber.controller;

import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    private static final int USERS_ON_ONE_PAGE = 10;

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin/userControl/{role}/{pageId}"}, method = RequestMethod.GET)
    public ModelAndView userControlPage(@PathVariable int pageId,@PathVariable String role) {
        ModelAndView model = new ModelAndView();

        List<User> userList = userService.findUsersBuRoleWithLimit(role, USERS_ON_ONE_PAGE, (pageId - 1) * USERS_ON_ONE_PAGE);
        model.addObject("users", userList);

        model.setViewName("admin/userControlPage");
        setUserNameIfExists(model);
        return model;
    }

    @RequestMapping(value = "/admin/userControl/{role}/disableUser/{userId}", method = RequestMethod.GET)
    public String disableUser(@PathVariable long userId,@PathVariable String role) {
        userService.disableUserById(userId);
        return "redirect:/admin/userControl/"+role+"/1";
    }

    @RequestMapping(value = "/admin/userControl/{role}/enableUser/{userId}", method = RequestMethod.GET)
    public String enableUser(@PathVariable long userId,@PathVariable String role) {
        userService.enableUserById(userId);
        return "redirect:/admin/userControl/"+role+"/1";
    }

    private void setUserNameIfExists(ModelAndView modelAndView) {
        String userName = getAuthentication().getName();
        if (!userName.equals("anonymousUser")) {
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("role", getAuthentication().getAuthorities().toString());
        }
    }
}
