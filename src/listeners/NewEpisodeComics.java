package listeners;

import setting_notifier_service.NotifierService;

public class NewEpisodeComics implements IListener {
    public String id;
    public String email;
    public NotifierService notifierService = NotifierService.getInstance();


    public NewEpisodeComics(String id, String email) {
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
