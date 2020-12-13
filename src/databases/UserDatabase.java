package databases;

import users.Admin;
import users.Client;
import users.User;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static UserDatabase instance;
    private Map<String, User> users = new HashMap<>();

    {
        Admin admin = new Admin("Root", "Admin", "admin", "123");
        Client client = new Client("Yerbolat", "Pazyl", "yerbolat@gmail.com", "123");

        users.put(admin.getLogin(), admin);
        users.put(client.getEmail(), client);
    }

    public static UserDatabase getInstance() {
        if (instance == null)
            instance = new UserDatabase();

        return instance;
    }

    public boolean hasLogin(String login) {
        return users.containsKey(login);
    }

    public boolean checkLoginAndPassword(String login, String password) {
        User user = users.get(login);

        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            return admin.getLogin().equals(login) && admin.getPassword().equals(password);
        }

        Client client = (Client) user;

        return client.getEmail().equals(login) && client.getPassword().equals(password);
    }

    public boolean isAdmin(String login) {
        return (users.get(login) instanceof Admin);
    }

    public void addUser(Admin admin) {
        users.put(admin.getLogin(), admin);
    }

    public void addUser(Client client) {
        users.put(client.getEmail(), client);
    }

    public User getUserByLogin(String login) {
        return users.get(login);
    }
}
