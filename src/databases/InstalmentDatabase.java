package databases;

import orders.Installment;
import orders.Order;

import java.util.ArrayList;

public class InstalmentDatabase {
    private static InstalmentDatabase instance;
    private ArrayList<Installment> installments = new ArrayList<>();

    private InstalmentDatabase() {}

    public static InstalmentDatabase getInstance() {
        if (instance == null)
            instance = new InstalmentDatabase();
        return instance;
    }

    public void addOrder(Installment installment) {
        installments.add(installment);
    }
}
