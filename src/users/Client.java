package users;

import decorators.NotifierEnum;
import strategies.CreditCard;

import java.util.HashSet;
import java.util.Set;

public class Client extends User {
    private String email;
    private String password;
    private String phoneNumber;
    private CreditCard creditCard;
    private Set<NotifierEnum> notifiers;

    public Client() {}

    public Client(String firstName, String lastname, String email, String password) {
        super(firstName, lastname);
        this.email = email;
        this.password = password;
        notifiers = new HashSet<>();
        notifiers.add(NotifierEnum.EMAIL);
    }

    public Client(String firstName, String lastname, String email, String password, CreditCard creditCard) {
        super(firstName, lastname);
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Set<NotifierEnum> getNotifiers() {
        return notifiers;
    }

    public void setNotifiers(Set<NotifierEnum> notifiers) {
        this.notifiers = notifiers;
    }
}
