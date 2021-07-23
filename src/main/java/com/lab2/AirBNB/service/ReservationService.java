package com.lab2.AirBNB.service;

import com.lab2.AirBNB.model.ReservationStatus;
import com.lab2.AirBNB.model.Reservations;
import com.lab2.AirBNB.model.User;

import java.text.ParseException;
import java.util.List;

public interface ReservationService {

    void save(Reservations reservations);

    List<Reservations> list();

    List<Reservations> getReservationByUsers(User user);

    List<Reservations> getReservationByStatus(ReservationStatus status);

    Reservations get(long id);


    int acceptedReservation();

    int declinedReservation();

    int pendingReservation();


    int januaryReservation() throws ParseException;

    int februaryReservation() throws ParseException;

    int marchReservation() throws ParseException;

    int aprilReservation() throws ParseException;

    int mayReservation() throws ParseException;

    int juneReservation() throws ParseException;

    int julyReservation() throws ParseException;

    int augustReservation() throws ParseException;

    int septemberReservation() throws ParseException;

    int octoberReservation() throws ParseException;

    int novemberReservation() throws ParseException;

    int decemberReservation() throws ParseException;
}
