package users;

public class Admin extends User {
    private String login;
    private String password;

    public Admin() {}

    public Admin(String firstName, String lastname, String login, String password) {
        super(firstName, lastname);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
