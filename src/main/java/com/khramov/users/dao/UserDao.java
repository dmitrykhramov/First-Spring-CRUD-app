package com.khramov.users.dao;

import com.khramov.users.model.User;

import java.util.List;

public interface UserDao {

    void addPerson(User p);
    void updatePerson(User p);
    List<User> listPersons();
    User getPersonById(int id);
    void removePerson(int id);
}
