package services;

import databases.UserDatabase;

public class LoginCheckHandler extends Handler {
    private UserDatabase userDatabase = UserDatabase.getInstance();

    @Override
    public boolean check(String login, String password) {
        if (userDatabase.hasLogin(login)) {
            return checkNext(login, password);
        }

        return false;
    }
}
