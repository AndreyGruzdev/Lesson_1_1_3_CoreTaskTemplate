package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "id int auto_increment, " +
                "name varchar(255) not null, " +
                "lastName varchar(255) not null, " +
                "age int not null, " +
                "constraint table_name_pk primary key (id))";

        try (Connection connect = Util.getConnection();
             PreparedStatement prepStatement = connect.prepareStatement(sql)) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при создании таблицы");
        }
    }

    public void dropUsersTable() throws SQLException {

        String sql = "drop TABLE IF EXISTS mydbtest.users";

        try (Connection connect = Util.getConnection();
             PreparedStatement prepStatement = connect.prepareStatement(sql)) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "INSERT INTO mydbtest.users (`name`, `lastname`, `age`) VALUES (?, ?, ?)";

        try (Connection connect = Util.getConnection();
             PreparedStatement prepStatement = connect.prepareStatement(sql)) {
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setByte(3, age);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при добавлении пользователя");
        }
    }

    public void removeUserById(long id) throws SQLException {

        String sql = "DELETE FROM mydbtest.users WHERE (`id` = ?)";

        try (Connection connect = Util.getConnection();
             PreparedStatement prepStatement = connect.prepareStatement(sql)) {
            prepStatement.setLong(1, id);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при удалении пользователя по id");
        }
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> listUsers = new ArrayList<>();
        String sql = "SELECT * FROM mydbtest.users";

        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            int i = 0;
            while (resultSet.next()) {
                listUsers.add(i, new User());
                listUsers.get(i).setId(resultSet.getLong(1));
                listUsers.get(i).setName(resultSet.getString(2));
                listUsers.get(i).setLastName(resultSet.getString(3));
                listUsers.get(i).setAge(resultSet.getByte(4));
                i++;
            }
        } catch (SQLException e) {
            throw new SQLException("Ошибка при получении списка пользователей");
        }
        return listUsers;
    }

    public void cleanUsersTable() throws SQLException {

        String sql = "TRUNCATE TABLE mydbtest.users";

        try (Connection connect = Util.getConnection();
             PreparedStatement prepStatement = connect.prepareStatement(sql)) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при очистке таблицы");
        }
    }
}
