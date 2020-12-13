package orders;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String clientEmail;
    private int cost;
    private Date data;
    private String address;

    public Order() {}

    public Order(String clientEmail, int cost, Date data, String address) {
        this.clientEmail = clientEmail;
        this.cost = cost;
        this.data = data;
        this.address = address;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
