package by.bsuir.kuhalski.buber.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("welcomePage");
        setUserNameIfExists(model);
        return model;
    }

    private void setUserNameIfExists(ModelAndView modelAndView) {
        String userName = getAuthentication().getName();
        if (!userName.equals("anonymousUser")) {
            modelAndView.addObject("userName", userName);
            boolean isAdmin = getAuthentication().getAuthorities().toString().contains("ROLE_ADMIN");
            modelAndView.addObject("isAdmin", isAdmin);
        }
    }

}
