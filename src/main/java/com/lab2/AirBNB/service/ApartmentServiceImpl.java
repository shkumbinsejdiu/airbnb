package com.lab2.AirBNB.service;

import com.lab2.AirBNB.model.Apartment;
import com.lab2.AirBNB.model.User;
import com.lab2.AirBNB.repository.ApartmentRepository;
import com.lab2.AirBNB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("apartmentService")
public class ApartmentServiceImpl implements ApartmentService {

    @Qualifier("apartmentRepository")
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Qualifier("userService")
    @Autowired
    private  UserService userService;

    @Override
    public void save(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    @Override
    public List<Apartment> list() {
        return apartmentRepository.findAll();
    }

//    @Override
//    public List<Apartment> getRoomsByUserId(User user) {
//        List<Apartment> userRooms = new ArrayList<>();
//        userRooms = apartmentRepository.findAllByUser(userService.currentUser());
//        Collections.sort(userRooms, Collections.reverseOrder());
//        return userRooms;
//    }

    @Override
    public Apartment get(long id) {
        return apartmentRepository.findById(id);
    }

}
