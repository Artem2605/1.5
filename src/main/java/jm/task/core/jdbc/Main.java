package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();

        UserServiceImpl user = new UserServiceImpl();

        user.createUsersTable();

        user.saveUser("Alex", "Ivanov", (byte) 26);
        user.saveUser("Masha", "Petrova", (byte) 20);
        user.saveUser("Vasya", "Romanov", (byte) 22);
        user.saveUser("Lena", "Leonova", (byte) 24);

        List<User> list = user.getAllUsers();
        for (User el: list) {
            System.out.println(el.toString());
        }

        user.cleanUsersTable();

        user.dropUsersTable();
    }
}
