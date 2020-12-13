package databases;

import orders.Order;

import java.util.ArrayList;

public class OrderDatabase {
    private static OrderDatabase instance;
    private ArrayList<Order> orders = new ArrayList<>();

    private OrderDatabase() {}

    public static OrderDatabase getInstance() {
        if (instance == null)
            instance = new OrderDatabase();
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
