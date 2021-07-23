package com.lab2.AirBNB.service;

import com.lab2.AirBNB.model.ReservationStatus;
import com.lab2.AirBNB.model.Reservations;
import com.lab2.AirBNB.model.User;
import com.lab2.AirBNB.repository.ReservationRepository;
import com.lab2.AirBNB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service("reservationServiceImpl")
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private  ReservationService reservationService;

    @Override
    public void save(Reservations reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservations> list() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservations> getReservationByUsers(User user) {
        List<Reservations> userReservation = new ArrayList<>();
        userReservation = reservationRepository.findAllByUsers(user);
        Collections.sort(userReservation, Collections.reverseOrder());
        return userReservation;
    }

    @Override
    public List<Reservations> getReservationByStatus(ReservationStatus status){
        return reservationRepository.findAllByStatus(status);
    }


    @Override
    public Reservations get(long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public int acceptedReservation() {
        return reservationRepository.findAllByStatus(ReservationStatus.ACCEPTED.ACCEPTED).size();
    }

    @Override
    public int declinedReservation() {
        return reservationRepository.findAllByStatus(ReservationStatus.CANCELED).size();
    }

    @Override
    public int pendingReservation() {
        return reservationRepository.findAllByStatus(ReservationStatus.PENDING).size();
    }

    @Override
    public int januaryReservation() throws ParseException {

        Calendar calendar = Calendar.getInstance();

        int countJanuary = 0;

        for (Reservations reservations : reservationRepository.findAll()) {

            calendar.setTime(reservations.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                countJanuary++;
            }

        }

        return countJanuary;
    }

    @Override
    public int februaryReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countFebruary = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY) {
                countFebruary++;
            }

        }

        return countFebruary;
    }

    @Override
    public int marchReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countMarch = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.MARCH) {
                countMarch++;
            }

        }

        return countMarch;
    }

    @Override
    public int aprilReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countApril = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.APRIL) {
                countApril++;
            }

        }

        return countApril;
    }

    @Override
    public int mayReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countMay = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.MAY) {
                countMay++;
            }

        }

        return countMay;
    }

    @Override
    public int juneReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countJune = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.JUNE) {
                countJune++;
            }

        }

        return countJune;
    }

    @Override
    public int julyReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countJuly = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.JULY) {
                countJuly++;
            }

        }

        return countJuly;
    }

    @Override
    public int augustReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countAugust = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.AUGUST) {
                countAugust++;
            }

        }

        return countAugust;
    }

    @Override
    public int septemberReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countSeptember = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
                countSeptember++;
            }

        }

        return countSeptember;
    }

    @Override
    public int octoberReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countOctober = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER) {
                countOctober++;
            }

        }

        return countOctober;
    }

    @Override
    public int novemberReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countNovember = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER) {
                countNovember++;
            }

        }

        return countNovember;
    }

    @Override
    public int decemberReservation() throws ParseException {
        Calendar calendar = Calendar.getInstance();

        int countDecember = 0;

        for (Reservations appointment : reservationRepository.findAll()) {
            calendar.setTime(appointment.getDateStartDate());
            if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
                countDecember++;
            }

        }

        return countDecember;
    }
}
