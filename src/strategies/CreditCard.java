package strategies;

public class  CreditCard {
    private int amount;
    private String number;
    private String date;
    private String cvv;

    public CreditCard(String number, String date, String cvv) {
        this.amount = 10000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\n[ CreditCard ]" +
                "\nAmount: " + amount +
                "\nNumber: " + number +
                "\nDate: " + date +
                "\nCVV: " + cvv;
    }
}
