package com.lab2.AirBNB.repository;

import com.lab2.AirBNB.model.ReservationStatus;
import com.lab2.AirBNB.model.Reservations;
import com.lab2.AirBNB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reservationRepository")
public interface ReservationRepository extends JpaRepository<Reservations,Long> {
    List<Reservations> findAll();

    List<Reservations> findAllByUsers(User user);

    Reservations findById(long id);

    List<Reservations> findAllByStatus(ReservationStatus status);
}
