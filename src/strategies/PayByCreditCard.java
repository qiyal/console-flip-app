package strategies;

import databases.OrderDatabase;
import databases.UserDatabase;
import orders.Order;
import users.Client;

import java.util.Date;
import java.util.Scanner;

public class PayByCreditCard implements PayStrategy {
    private OrderDatabase orderDatabase = OrderDatabase.getInstance();
    private UserDatabase userDatabase = UserDatabase.getInstance();
    private Client client;
    private int cost;
    private String address;
    private Scanner sc = new Scanner(System.in);

    @Override
    public boolean pay() {
        if (client.getCreditCard() == null) {
            System.out.println("\nYou don't have a card!");
            return false;
        } else if (cost > client.getCreditCard().getAmount()) {
            System.out.println("\nYour deposit smaller than cost order.");
            return false;
        } else {
            System.out.println("\nPaying " + cost + "tg using Credit Card.");
            client.getCreditCard().setAmount(client.getCreditCard().getAmount() - cost);
            Order order = new Order(client.getEmail(), cost, new Date(), address);
            userDatabase.addUser(client);
            orderDatabase.addOrder(order);
            return true;
        }
    }

    @Override
    public void collectPaymentDetails(int totalCost, Client client) {
        this.client = client;
        this.cost = totalCost;

        if(client.getCreditCard() == null) {
            System.out.print("\nEnter the card number: ");
            String number = sc.next();
            System.out.print("Enter the card expiration date 'mm/yy': ");
            String date = sc.next();
            System.out.print("Enter the CVV code: ");
            String cvv = sc.next();

            this.client.setCreditCard(new CreditCard(number, date, cvv));
            userDatabase.addUser(this.client);
        }

        System.out.println("\nDelivery address: ");
        System.out.print("enter: ");
        address = sc.next();
    }
}
