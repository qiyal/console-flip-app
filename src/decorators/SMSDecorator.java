package decorators;

public class SMSDecorator extends NotifierDecorator {
    private String phoneNumber;

    public SMSDecorator() {}

    public SMSDecorator(Notifier wrapper, String phoneNumber) {
        super(wrapper);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        System.out.println("\nSent a SMS for: " + phoneNumber);
        System.out.println("SMS: " + message);
    }
}
