package credit_card;

public class CreditCard {
    private String cardNumber;
    private String data;
    private String cvvCode;

    public CreditCard() {}

    public CreditCard(String cardNumber, String data, String cvvCode) {
        this.cardNumber = cardNumber;
        this.data = data;
        this.cvvCode = cvvCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }
}
