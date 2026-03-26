package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.User;

import java.util.List;

public class UserService {

    private final UserDao userDao = new UserDao();

    public List<User> getAllUsers(){
        return userDao.findAll();
    }
}
