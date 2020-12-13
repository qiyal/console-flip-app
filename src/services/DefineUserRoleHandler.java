package services;

import databases.UserDatabase;

public class DefineUserRoleHandler extends Handler {
    private UserDatabase userDatabase = UserDatabase.getInstance();
    private AuthService authService = AuthService.getInstance();

    @Override
    public boolean check(String login, String password) {
        if (userDatabase.isAdmin(login)) {
            authService.setRole(Role.ADMIN);
        } else {
            authService.setRole(Role.CLIENT);
        }

        return checkNext(login, password);
    }
}
