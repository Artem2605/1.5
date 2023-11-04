package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;
import static jm.task.core.jdbc.util.Util.*;

public class Main {

    public static void main(String[] args) {
        getSessionFactory();
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Alex", "Ivanov", (byte) 26);
        user.saveUser("Masha", "Petrova", (byte) 20);
        user.saveUser("Vasya", "Romanov", (byte) 22);
        user.saveUser("Lena", "Leonova", (byte) 24);
        user.removeUserById(1);
        List<User> list = user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
        quitSessionFactory();
    }
}