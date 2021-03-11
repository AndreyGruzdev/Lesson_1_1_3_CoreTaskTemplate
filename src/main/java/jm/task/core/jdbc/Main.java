package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        UserServiceImpl users = new UserServiceImpl();
        users.createUsersTable();
        users.saveUser("Igor", "Gron", (byte) 18);
        users.saveUser("Andrey", "Gry", (byte) 25);
        users.saveUser("Lexa", "Vaf", (byte) 48);
        users.saveUser("Alex", "Mers", (byte) 55);
        System.out.println(users.getAllUsers());
        users.cleanUsersTable();
        users.dropUsersTable();
    }
}
