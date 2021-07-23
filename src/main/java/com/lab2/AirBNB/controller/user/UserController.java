package com.lab2.AirBNB.controller.user;

import com.lab2.AirBNB.model.Apartment;
import com.lab2.AirBNB.model.Reservations;
import com.lab2.AirBNB.model.User;
import com.lab2.AirBNB.repository.ReservationRepository;
import com.lab2.AirBNB.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private  ReservationService reservationService;
    @Autowired
    private  ApartmentService apartmentService;
    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {

        ModelAndView model = new ModelAndView();
        model.setViewName("auth/login");

        return model;
    }

    @RequestMapping("/userData")
    public String userData(Model model) {

        User user = userService.currentUser();
        if (user == null) {
            user = new User();
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        return "user/user-data";
    }

    @RequestMapping(value = {"/registerUserData"}, method = RequestMethod.POST)
    public String registerUserData(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User edit = userService.findUserByUsername(auth.getName());
        if (bindingResult.hasErrors()) {
            return "user/user-data";
        } else {
            edit.setUsername(user.getUsername());
            edit.setEmail(user.getEmail());
            userService.updateUser(edit);
            return "user/user";
        }
    }
    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        User current = userService.currentUser();
        List<Apartment> apartmentList = apartmentService.list();

        model.addObject("user", user);
        model.addObject("listApartments", apartmentList);
        model.setViewName("user/user");
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

    @RequestMapping("/userApartments")
    public String apartments(Model model) {

        List<Apartment> listApartments = apartmentService.list();
        model.addAttribute("listUserApartments", listApartments);

        return "user/listApartments";
    }

    @RequestMapping("/logouT")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

//        List<Reservations> expiredReservation = reservationService.list();
//        for (Reservations reservation: expiredReservation
//             ) {
//            Date now = new Date(System.currentTimeMillis());
//            if (now.compareTo(reservation.getEnd_date())<0);
//            reservationRepository.delete(reservation);
//        }

        Map<String, Integer> reservationGraph = new LinkedHashMap<>();

        reservationGraph.put("January", 2);
        reservationGraph.put("February", 3);
        reservationGraph.put("March", 12);
        reservationGraph.put("April", 2);
        reservationGraph.put("May", 12);
        reservationGraph.put("June", 22);
        reservationGraph.put("July", 33);
        reservationGraph.put("August", 22);
        reservationGraph.put("September", 33);
        reservationGraph.put("October", 22);
        reservationGraph.put("November", 2);
        reservationGraph.put("December", 50);

        int countUsers = userService.list().size();
        int countAppartments = apartmentService.list().size();
        int countReservations = reservationService.list().size();

        model.addAttribute("appointmentGraph", reservationGraph);
        model.addAttribute("user", user);
        model.addAttribute("countUsers", countUsers);
        model.addAttribute("countAppartments", countAppartments);
        model.addAttribute("countReservations", countReservations);
        model.addAttribute("accepted", 10);
        model.addAttribute("declined", 12);
        model.addAttribute("pending", 13);

        return "admin/admin-dashboard";
    }

}
