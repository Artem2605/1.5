package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static String createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n" +
            "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `lastName` VARCHAR(45) NOT NULL,\n" +
            "  `age` TINYINT NOT NULL,\n" +
            "  PRIMARY KEY (`ID`))";
    private static String dropUsersTable = "DROP TABLE IF EXISTS users";
    private static List<User> allUsers = null;
    private static final SessionFactory SESSION_FACTORY = new Util().getSessionFactory();

    @Override
    public void createUsersTable() {
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(createUsersTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(dropUsersTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, Byte age) {
        User user = new User(name, lastName, age);
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(Long id) {
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User where id = :param").setParameter("param", id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();
            allUsers = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}