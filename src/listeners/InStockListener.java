package listeners;

import setting_notifier_service.NotifierService;

public class InStockListener implements IListener {
    public String id;
    public String email;
    public NotifierService notifierService = NotifierService.getInstance();

    public InStockListener(String id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public void notify(String event, String message) {
        if (id.equals(event)) {
            notifierService.notifier(message, email);
        }
    }
}
