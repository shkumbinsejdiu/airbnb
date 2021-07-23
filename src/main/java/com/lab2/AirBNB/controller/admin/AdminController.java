package com.lab2.AirBNB.controller.admin;


import com.lab2.AirBNB.model.Apartment;
import com.lab2.AirBNB.service.ApartmentServiceImpl;
import com.lab2.AirBNB.service.ReservationService;
import com.lab2.AirBNB.service.UserServiceImpl;
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
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImp;
    @Autowired
    private ApartmentServiceImpl apartmentServiceImpl;
    @Autowired
    private ReservationService reservationService;



    @RequestMapping("/users")
    public String users(Model model) {
        User user = userService.currentUser();
        List<User> listUsers = userServiceImp.list();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("user",user);

        return "admin/listUsers";
    }



    @RequestMapping("/registerUser")
    public String registerUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "admin/addUser";
    }
    @RequestMapping(value = {"/registerUserAdmin"}, method = RequestMethod.POST)
    public ModelAndView registerUserAmin(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "This username already exists");
        }
        if (bindingResult.hasErrors()) {
            model.addObject("user",new User());
            model.setViewName("admin/addUser");

        } else {
            userServiceImp.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("redirect:/admin");
        }
        return model;
    }


}