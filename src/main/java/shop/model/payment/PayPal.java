package shop.model.payment;

public class PayPal implements PaymentPolicy {

    private final String eMail;

    public PayPal(String eMail) {
        this.eMail = eMail.replaceAll("\\s", "");
    }

    @Override
    public void pay(String orderId, double amount) {
        System.out.println(orderId + ": Please transfer " + amount + " to our paypal " + eMail + ".\n");
    }

    @Override
    public String toString() {
        return "PayPal Transfer, contact: " + eMail;
    }
}
