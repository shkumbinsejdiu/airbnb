package com.lab2.AirBNB.controller.apartment;

import com.lab2.AirBNB.model.Apartment;
import com.lab2.AirBNB.model.User;
import com.lab2.AirBNB.repository.ApartmentRepository;
import com.lab2.AirBNB.service.ApartmentService;
import com.lab2.AirBNB.service.ApartmentServiceImpl;
import com.lab2.AirBNB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private ApartmentServiceImpl apartmentServiceImpl;
    @Autowired
    private UserService userService;
    @Autowired
    private ApartmentRepository apartmentRepository;

    private Apartment apartment;

    @RequestMapping("/addApartment")
    public String registerApartments(Model model) {

        User user = userService.currentUser();
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("user",user);

        return "admin/addApartment";
    }

    @RequestMapping(value = {"/addApartmentAdmin"}, method = RequestMethod.POST)
    public ModelAndView addApartment(@Valid Apartment apartment, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User user = userService.currentUser();
        apartment.setPublished_at(new Date(System.currentTimeMillis()));
        if (bindingResult.hasErrors()) {
            model.addObject("appartment",new Apartment());
            model.setViewName("admin/addApartment");
            model.addObject("user",user);
        } else {
            apartmentService.save(apartment);
            model.addObject("msg", "Apartmen has been added successfully!");
            model.addObject("apartment", new Apartment());
            model.addObject("user",user);
            model.setViewName("redirect:/admin");
        }
        return model;
    }

    @RequestMapping("/apartments")
    public String apartments(Model model) {

        User user = userService.currentUser();
        List<Apartment> listApartments = apartmentService.list();
        model.addAttribute("listApartments", listApartments);
        model.addAttribute("user",user);

        return "admin/listApartments";
    }

    @RequestMapping(value = "/delete_apartment/{id}", method = RequestMethod.GET)
    public String deleteApartments(@PathVariable Long id) {
        Apartment apartment = apartmentServiceImpl.get(id);

        apartmentRepository.delete(apartment);
        return "redirect:/apartments";
    }

    @RequestMapping(value= "/edit_apartment/{id}", method = RequestMethod.GET)
    public String editApartment(@PathVariable("id") long id, Model model ) {
        Apartment apartment = apartmentServiceImpl.get(id);
        User user = userService.currentUser();
        model.addAttribute("apartment", apartment);
        model.addAttribute("user",user);
        return  "admin/editApartment";
    }

    @PostMapping(value="/update" )
    public String updateApartment(@ModelAttribute("apartment") Apartment apartment ,Model model) {
        Apartment apartment1 = apartment;
        User user = userService.currentUser();
        apartmentService.save(apartment);
        model.addAttribute("user",user);
        model.addAttribute("apartment", new Apartment());
        return  "redirect:/apartments";

    }

}
