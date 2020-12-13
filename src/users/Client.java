package users;


import credit_card.CreditCard;

public class Client extends User {
    public String email;
    public String password;
    public CreditCard creditCard;

    public Client() {}

    public Client(String firstName, String lastname, String email, String password) {
        super(firstName, lastname);
        this.email = email;
        this.password = password;
    }

    public Client(String firstName, String lastname, String email, String password, CreditCard creditCard) {
        super(firstName, lastname);
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
