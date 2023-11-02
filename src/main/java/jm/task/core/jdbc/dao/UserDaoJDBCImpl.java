package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String command = "CREATE TABLE users (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`))";
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String command = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            String command = "INSERT INTO users (name, lastname, age) Values (?, ?, ?)";
            PreparedStatement preparedStatement  = connection.prepareStatement(command);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            String command = "DELETE FROM users WHERE ID = ?";
            PreparedStatement preparedStatement  = connection.prepareStatement(command);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String command = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String command = "DELETE FROM users";
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}