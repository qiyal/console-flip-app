package decorators;

public class NotifierDecorator implements Notifier {
    private Notifier wrapper;

    public NotifierDecorator() {}

    public NotifierDecorator(Notifier wrapper) {
        this.wrapper = wrapper;
    }

    public void setWrapper(Notifier wrapper) {
        this.wrapper = wrapper;
    }

    public Notifier getWrapper() {
        return wrapper;
    }

    @Override
    public void send(String message) {
        if (wrapper != null)
            wrapper.send(message);
    }
}
