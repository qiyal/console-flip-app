package strategies;

import databases.OrderDatabase;
import orders.Order;
import users.Client;

import java.util.Date;
import java.util.Scanner;

public class PayByCash implements PayStrategy {
    private OrderDatabase orderDatabase = OrderDatabase.getInstance();
    private Client client;
    private int cost;
    private String address;
    private Scanner sc = new Scanner(System.in);

    @Override
    public boolean pay() {
        Order order = new Order(client.getEmail(), cost, new Date(), address);
        orderDatabase.addOrder(order);
        System.out.println("Paying " + cost + "tg.");
        System.out.println("\nThanks for your purchase, expect delivery.");
        return true;
    }

    @Override
    public void collectPaymentDetails(int totalCost, Client client) {
        this.client = client;
        cost = totalCost;
        System.out.println("\nDelivery address: ");
        System.out.print("enter: ");
        address = sc.next();
    }
}
