package com.lab2.AirBNB.controller.admin;

import com.lab2.AirBNB.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class GraphController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservationGraph")
    public String reservationGraph(Model model) {

        Map<String, Integer> reservationGraph = new LinkedHashMap<>();

        reservationGraph.put("Accepted", reservationService.acceptedReservation());
        reservationGraph.put("Declined", reservationService.declinedReservation());
        reservationGraph.put("Pending", reservationService.pendingReservation());

        model.addAttribute("reservationGraph", reservationGraph);

        return "reservationGraph";
    }

}