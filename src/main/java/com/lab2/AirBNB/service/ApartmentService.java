package com.lab2.AirBNB.service;

import com.lab2.AirBNB.model.Apartment;
import com.lab2.AirBNB.model.User;

import java.util.List;

public interface ApartmentService {

    void save(Apartment apartment);

    List<Apartment> list();

//    List<Apartment> getRoomsByUserId(User user);

    Apartment get(long id);

}
