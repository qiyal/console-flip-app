package strategies;

import users.Client;

public interface PayStrategy {
    boolean pay();
    void collectPaymentDetails(int totalCost, Client client);
}
