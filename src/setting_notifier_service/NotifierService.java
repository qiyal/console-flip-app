package setting_notifier_service;

import databases.UserDatabase;
import decorators.*;
import services.AuthService;
import users.Client;

import java.util.Scanner;

public class NotifierService {
    private static NotifierService instance;
    private UserDatabase userDatabase = UserDatabase.getInstance();
    private AuthService authService = AuthService.getInstance();
    private Scanner sc = new Scanner(System.in);

    private NotifierService() {}

    public static NotifierService getInstance() {
        if (instance == null)
            instance = new NotifierService();
        return instance;
    }

    public void notifier(String message, String email) {
        Client client = (Client) userDatabase.getUserByLogin(authService.getAuthUserLogin());

        NotifierDecorator notifier = new NotifierDecorator();

        if (client.getNotifiers().contains(NotifierEnum.EMAIL)) {
            notifier = new NotifierDecorator(new EmailNotifier(client.getEmail()));
        }

        if (client.getNotifiers().contains(NotifierEnum.EMAIL)) {
            notifier = new SMSDecorator(notifier, client.getPhoneNumber());
        }

        notifier.send(message);
    }
}
