package com.lab2.AirBNB.repository;

import com.lab2.AirBNB.model.Apartment;
import com.lab2.AirBNB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("apartmentRepository")
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findAll();

    Apartment findById(long id);

}
