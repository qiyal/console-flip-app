package orders;

import java.util.Date;

public class Installment {
    private Order order;
    private Date endDay;
    private int monthPayVale;

    public Installment(Order order) {
        Date date = order.getData();
        date.setYear(date.getYear() + 1);
        this.order = order;
        this.endDay = date;
        this.monthPayVale = order.getCost() / 12;
    }

    public Order getOrder() {
        return order;
    }

    public Date getEndDay() {
        return endDay;
    }

    public int getMonthPayVale() {
        return monthPayVale;
    }
}
