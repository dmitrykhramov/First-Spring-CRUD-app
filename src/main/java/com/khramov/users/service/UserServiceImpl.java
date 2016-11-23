package com.khramov.users.service;

import com.khramov.users.dao.UserDao;
import com.khramov.users.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDAO;

    public void setUserDAO(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addPerson(User p) {
        p.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
        this.userDAO.addPerson(p);
    }

    @Override
    @Transactional
    public void updatePerson(User p) {
        p.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
        this.userDAO.updatePerson(p);
    }

    @Override
    @Transactional
    public List<User> listPersons() {
        return this.userDAO.listPersons();
    }

    @Override
    @Transactional
    public List<User> listPersons(int page) {
        List<User> list = this.userDAO.listPersons();
        List<User> result = new ArrayList<>();
        for (int i = page * 10; i < (page + 1) * 10; i++) {
            if (i < list.size())
                result.add(list.get(i));
        }
        return result;
    }

    @Override
    @Transactional
    public User getPersonById(int id) {
        return this.userDAO.getPersonById(id);
    }

    @Override
    @Transactional
    public void removePerson(int id) {
        this.userDAO.removePerson(id);
    }
}
