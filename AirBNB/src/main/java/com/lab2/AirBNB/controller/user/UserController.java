package com.lab2.AirBNB.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lab2.AirBNB.model.User;
import com.lab2.AirBNB.service.UserService;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {

        ModelAndView model = new ModelAndView();
        model.setViewName("auth/login");

        return model;
    }


    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("auth/register");

        return model;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("auth/register");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("auth/login");
        }
        return model;
    }


    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView admin(ModelAndView model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        model.addObject("user", user);
        model.setViewName("admin/admin-dashboard");
        return model;
    }
}