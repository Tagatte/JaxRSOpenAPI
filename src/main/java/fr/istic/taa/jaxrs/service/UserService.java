package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Concert;

import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.ConcertBaseDto;
import fr.istic.taa.jaxrs.dto.ConcertCreateDto;
import fr.istic.taa.jaxrs.dto.PersonBaseDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.PathParam;

import java.util.List;

public class UserService {

    private User fromDtoToUserMapper(PersonBaseDto personBaseDto, User user)
    {
        user.setFirstName(personBaseDto.getFirstName());
        user.setLastName(personBaseDto.getLastName());
        return user;
    }

    private final UserDao userDao = new UserDao();

    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public User getUser(@PathParam("id") long id){
        return userDao.findOne(id);
    }

    public User createUser(PersonCreateDto personCreateDto) {
        User user = fromDtoToUserMapper(personCreateDto, new User());
        userDao.save(user);
        return user;
    }

    public User updateUser(long id, PersonUpdateDto personUpdateDto){
        if (!(id == personUpdateDto.getId())) {
            throw new BadRequestException("User non accessible");
        }
        User user = fromDtoToUserMapper(personUpdateDto,getUser(id));
        userDao.update(user);
        return user;
    }

    public void deleteUser(long id){
        userDao.deleteById(id);
    }


}
