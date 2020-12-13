package listeners;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EventManeger {
    Map<String, HashSet<IListener>> listeners = new HashMap<>();

    public EventManeger() {
        listeners.put("in-stock", new HashSet<>());
        listeners.put("new-episode", new HashSet<>());
        listeners.put("new-sale", new HashSet<>());
    }

    public void subscribe(String eventType, IListener listener) {
        HashSet<IListener> users = listeners.get(eventType);

        for (IListener user : users) {
            if(eventType.equals("in-stock")) {
                InStockListener l1 = (InStockListener) listener;
                InStockListener l2 = (InStockListener) user;
                if (l1.email.equals(l2.email) && l1.id.equals(l2.id)) {
                    System.out.println("\nAlready available!");
                    return;
                }
            } else if (eventType.equals("new-episode")) {
                NewEpisodeComics l1 = (NewEpisodeComics) listener;
                NewEpisodeComics l2 = (NewEpisodeComics) user;
                if (l1.email.equals(l2.email) && l1.id.equals(l2.id)) {
                    System.out.println("\nAlready available!");
                    return;
                }
            }else {
                NewSalesListener l1 = (NewSalesListener) listener;
                NewSalesListener l2 = (NewSalesListener) user;
                if (l1.email.equals(l2.email)) {
                    System.out.println("\nAlready available!");
                    return;
                }
            }
        }
        System.out.println("\nNew listener added!");
        listeners.get(eventType).add(listener);
    }

    public void unsubscribe(String eventType, IListener listener) {
        HashSet<IListener> users = listeners.get(eventType);

        for (IListener user : users) {
            if(eventType.equals("in-stock")) {
                InStockListener l1 = (InStockListener) listener;
                InStockListener l2 = (InStockListener) user;
                if (l1.email.equals(l2.email) && l1.id.equals(l2.id)) {
                    listeners.get(eventType).remove(l2);
                    System.out.println("\nListener deleted!");
                    return;
                }
            } else if(eventType.equals("new-episode")) {
                NewEpisodeComics l1 = (NewEpisodeComics) listener;
                NewEpisodeComics l2 = (NewEpisodeComics) user;
                if (l1.email.equals(l2.email) && l1.id.equals(l2.id)) {
                    listeners.get(eventType).remove(l2);
                    System.out.println("\nListener deleted!");
                    return;
                }
            } else {
                NewSalesListener l1 = (NewSalesListener) listener;
                NewSalesListener l2 = (NewSalesListener) user;
                if (l1.email.equals(l2.email)) {
                    listeners.get(eventType).remove(l2);
                    System.out.println("\nListener deleted!");
                    return;
                }
            }
        }
        System.out.println("\nCan not found!");
    }

    public void notifyUsers(String id, String event, String message) {
        HashSet<IListener> users = listeners.get(event);

        for (IListener listener : users) {
            listener.notify(id, message);
        }
    }
}
