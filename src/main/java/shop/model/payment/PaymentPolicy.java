package shop.model.payment;

public interface PaymentPolicy {

    void pay(String orderId, double amount);

    String toString();
}
