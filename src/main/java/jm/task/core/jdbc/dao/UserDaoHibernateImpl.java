package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory fabric = getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = fabric.getCurrentSession()) {
            String command = "CREATE TABLE users (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`))";
            session.beginTransaction();
            session.createSQLQuery(command).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = fabric.getCurrentSession()) {
            String command = "DROP TABLE IF EXISTS users";
            session.beginTransaction();
            session.createSQLQuery(command).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try(Session session = fabric.getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = fabric.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User where id = :param").setParameter("param", id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try(Session session = fabric.getCurrentSession()) {
            session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = fabric.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
