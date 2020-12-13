package decorators;

public class EmailNotifier implements Notifier {
    private String email;

    public EmailNotifier() {}

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void send(String message) {
        System.out.println("\nSent a message to email: " + email);
        System.out.println("Message: " + message);
    }
}
