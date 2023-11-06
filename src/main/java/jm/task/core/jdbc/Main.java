package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Alex", "Ivanov", Byte.valueOf("26"));
        user.saveUser("Masha", "Petrova", Byte.valueOf("20"));
        user.saveUser("Vasya", "Romanov", Byte.valueOf("22"));
        user.saveUser("Lena", "Leonova", Byte.valueOf("24"));
        user.removeUserById(Long.valueOf("1"));
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
        new Util().quitSessionFactory();
    }
}