package listeners;

import setting_notifier_service.NotifierService;

public class NewSalesListener implements IListener {
    public String email;
    public NotifierService notifierService = NotifierService.getInstance();


    public NewSalesListener(String email) {
        this.email = email;
    }

    @Override
    public void notify(String event, String message) {
        notifierService.notifier(message, email);
    }
}
