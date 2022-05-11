package shop.model.payment;

public class CreditCard implements PaymentPolicy {

    private final String cardno;

    public CreditCard(String cardno) {
        this.cardno = cardno.replaceAll("\\D+", "");
    }

    @Override
    public void pay(String orderId, double amount) {
        System.out.println(orderId + ": The " + amount + " will be taken from your credit card.\n");
    }

    @Override
    public String toString() {
        return "CreditCard #" + cardno;
    }
}
