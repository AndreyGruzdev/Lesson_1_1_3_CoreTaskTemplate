package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() throws SQLException {
        new UserDaoJDBCImpl().createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        new UserDaoJDBCImpl().dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        new UserDaoJDBCImpl().saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        new UserDaoJDBCImpl().removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return new UserDaoJDBCImpl().getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        new UserDaoJDBCImpl().cleanUsersTable();
    }
}
