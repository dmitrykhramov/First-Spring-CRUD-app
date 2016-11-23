package com.khramov.users.dao;

import com.khramov.users.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addPerson(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("User saved successfully, User Details="+p);
    }

    @Override
    public void updatePerson(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("User updated successfully, User Details="+p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listPersons() {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "from User";
        List<User> personsList = session.createQuery(query).list();
        for(User p : personsList){
            logger.info("User List::"+p);
        }
        return personsList;
    }

    @Override
    public User getPersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded successfully, User details="+p);
        return p;
    }

    @Override
    public void removePerson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        logger.info("User deleted successfully, User details="+p);
    }
}
