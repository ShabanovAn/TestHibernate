package servise;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.UserDAO;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


import java.sql.*;
import java.util.List;
public class UserServise extends SessionUtil implements UserDAO {
    Session session;
    Transaction transaction;


    //Hibernate
    public void add(User user) throws SQLException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }
    //Sql
    public List<User> getAll() throws SQLException {
      return getSession().createNativeQuery("select * from user", User.class)
              .getResultList();
    }
    public List<User> getUserID() {
        return getSession().createNativeQuery("select * from user where id = :id", User.class)
                .getResultList();
    }

    //HQL
    public List<User> getAllHql() throws SQLException {
        return getSession().createQuery("from User", User.class)
                .getResultList();
    }

    public User getid(int id)  {
//        Session session = getSession();
//        Query query = session.createQuery("from User where id = :id", User.class);
//        query.setParameter("id", id);
//        User user = (User) query.uniqueResult();
//        return user;
        return getSession()
                .createQuery("from User where id = :id", User.class)
                .setParameter("id", id)
                .uniqueResult();

    }

    //Criteria
    public User getById(Long id) throws SQLException {
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.like("id", id))
                .uniqueResult();
        return user;
    }

    public User getFirstName(String firstName) {
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.like("name", firstName))
                .uniqueResult();

    }

    public void update(User user) throws SQLException {
        openTransactionSession();
        getSession().saveOrUpdate(user);
        closeTransactionSessition();
    }

    public void remove(User user) throws SQLException {
        openTransactionSession();
        getSession().remove(user);
        closeTransactionSessition();
    }
}
