package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;
import org.example.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.query.Query;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;



    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = false)
    @Override
    public List<User> getUsersByCarModelAndSeries(String carModel, int carSeries) {
        return userDao.getUsersByCarModelAndSeries(carModel, carSeries);
    }
}