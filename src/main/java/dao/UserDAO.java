package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    //create
    void add(User user) throws SQLException;

    //read
    List<User> getAll() throws SQLException;

    User getById(Long id) throws SQLException;

    //update
    void update(User user) throws SQLException;

    //delete
    void remove(User user) throws SQLException;

}


