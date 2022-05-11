package shop.model.payment;

public class Transfer implements PaymentPolicy {

    private final String eMail;

    public Transfer(String eMail) {
        this.eMail = eMail.replaceAll("\\s", "");
    }

    @Override
    public void pay(String orderId, double amount) {
        System.out.println(orderId + ": Please transfer " + amount + " to our account 12DE456789|000123.\n");
    }

    @Override
    public String toString() {
        return "Bank Transfer, contact: " + eMail;
    }
}
