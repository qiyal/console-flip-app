package services;

public abstract class Handler {
    private Handler next;

    public void setNext(Handler nextHandler) {
        this.next = nextHandler;
    }

    public abstract boolean check(String login, String password);

    protected boolean checkNext(String login, String password) {
        if(next == null) {
            return true;
        }

        return next.check(login, password);
    }
}
