package org.example.service;

import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    void add(User user);
    List<User> listUsers();
    List<User> getUsersByCarModelAndSeries(String carModel, int carSeries);
}
