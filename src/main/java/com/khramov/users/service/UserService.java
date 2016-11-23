package com.khramov.users.service;

import com.khramov.users.model.User;

import java.util.List;

public interface UserService {

    void addPerson(User p);
    void updatePerson(User p);
    List<User> listPersons();
    List<User> listPersons(int page);
    User getPersonById(int id);
    void removePerson(int id);
}
