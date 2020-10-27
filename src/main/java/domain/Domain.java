package domain;

import bl.HibernateUtil;
import entity.User;
import servise.UserServise;

import java.sql.SQLException;

public class Domain {
    public static void main(String[] args) throws SQLException {
       // HibernateUtil.getSessionFactory();
        UserServise userServise = new UserServise();
        User user = new User();
        user.setId(1);
        user.setName("Tom");
        user.setLname("Kot");
        userServise.add(user);




    }
}
