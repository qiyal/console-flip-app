package strategies;

import databases.InstalmentDatabase;
import databases.OrderDatabase;
import orders.Installment;
import orders.Order;
import users.Client;

import java.util.Date;
import java.util.Scanner;

public class PayByInstalments implements PayStrategy {
    private OrderDatabase orderDatabase = OrderDatabase.getInstance();
    private InstalmentDatabase instalmentDatabase = InstalmentDatabase.getInstance();
    private Client client;
    private int cost;
    private String address;
    private Scanner sc = new Scanner(System.in);

    @Override
    public boolean pay() {
        Order order = new Order(client.getEmail(), cost, new Date(), address);
        Installment installment = new Installment(order);
        orderDatabase.addOrder(order);
        instalmentDatabase.addOrder(installment);
        System.out.println("\nInstallment arranged. Mouth pay value: " + installment.getMonthPayVale());
        return true;
    }

    @Override
    public void collectPaymentDetails(int totalCost, Client client) {
        this.cost = totalCost;
        this.client = client;

        System.out.println("\nDelivery address: ");
        System.out.print("enter: ");
        address = sc.next();
    }
}
