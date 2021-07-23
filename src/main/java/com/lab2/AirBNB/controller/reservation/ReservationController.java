package com.lab2.AirBNB.controller.reservation;

import com.lab2.AirBNB.model.*;
import com.lab2.AirBNB.repository.ReservationRepository;
import com.lab2.AirBNB.service.ApartmentService;
import com.lab2.AirBNB.service.ReservationService;
import com.lab2.AirBNB.service.UserService;
import com.lab2.AirBNB.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

@Controller
public class ReservationController {
    @Autowired
    private Optional<User> user;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ApartmentService apartmentService;






    @RequestMapping(value = "/createReservation/{id}", method = RequestMethod.GET)
    public String createReservation(@PathVariable(name = "id") long id,Model model) {
        User user = userService.currentUser();
        Reservations reservation = new Reservations();
        Apartment apartment = apartmentService.get(id);

        model.addAttribute("apartment",apartment);
        model.addAttribute("reservation",new Reservations());
        model.addAttribute("user",user);

        return  "user/makeReservation";
    }

    @PostMapping(value = "/saveReservation/{id}")
    public String saveAppointment(@ModelAttribute("reservation") Reservations reservation,@PathVariable(name = "id") long id, Model model) throws ParseException {

        if (id == 0) {
            return "user/listApartments";
        } else {
            Apartment apartment = apartmentService.get(id);
            User currentUser = userService.currentUser();
            reservation.setStatus(ReservationStatus.PENDING);
            reservation.setApartment(apartment);
            reservation.setPrice(apartment.getPrice());
            reservation.setUsers(new HashSet<User>(Arrays.asList(currentUser)));
            reservationRepository.save(reservation);
            model.addAttribute("user",user);
            model.addAttribute("apartment",new Apartment());
            return "redirect:/user";
        }
    }

    @RequestMapping("/userReservations")
    public String viewUserReservation(Model model) {

        User user = userService.currentUser();

        List<Reservations> listUserReservations = reservationRepository.findAllByUsers(user);
        model.addAttribute("listUserReservations", listUserReservations);
        model.addAttribute("user",user);

        return "user/listUserReservation";
    }

    @RequestMapping("/userAdminReservations/{id}")
    public String viewUserAdminReservation(@PathVariable(name = "id") long id,Model model) {

        User user = userService.currentUser();
        User temp = userService.get(id);

        List<Reservations> listAdminUserReservations = reservationRepository.findAllByUsers(temp);
        model.addAttribute("listUserAdminReservations", listAdminUserReservations);
        model.addAttribute("user",user);

        return "admin/listUserReservation";
    }


    @RequestMapping("/pendingReservations")
    public String viewPendingReservations(Model model) {


        List<Reservations> listPendingReservations = reservationService.getReservationByStatus(ReservationStatus.PENDING);
        model.addAttribute("listPendingReservations", listPendingReservations);

        return "/admin/listPendingReservation";
    }

    @RequestMapping("/reservations")
    public String viewReservations(Model model) {


        List<Reservations> listReservations = reservationService.list();
        model.addAttribute("listReservations", listReservations);
        model.addAttribute("user",user);

        return "/admin/listReservations";
    }

    @RequestMapping(value = "/acceptReservation/{id}", method = RequestMethod.GET)
    public String acceptReservation(@PathVariable(name = "id") Long id, Model model) {

        Reservations reservationDB = new Reservations();
        reservationDB = reservationService.get(id);
        if (reservationDB.getStatus().equals(ReservationStatus.PENDING)) {
            reservationDB.setStatus(ReservationStatus.ACCEPTED);
            reservationService.save(reservationDB);
        } else {
            System.out.println("cant do it");
        }

        return "redirect:/reservations";
    }

    @RequestMapping(value = "/declineReservation/{id}", method = RequestMethod.GET)
    public String declineReservation(@PathVariable(name = "id") Long id, Model model) {

        Reservations reservationDB = new Reservations();
        reservationDB = reservationService.get(id);
        if (reservationDB.getStatus().equals(ReservationStatus.PENDING)) {
            reservationDB.setStatus(ReservationStatus.CANCELED);
            reservationService.save(reservationDB);
        } else {
            System.out.println("cant do it");
        }

        return "redirect:/reservations";
    }
}
