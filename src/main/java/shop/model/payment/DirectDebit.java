package shop.model.payment;

public class DirectDebit implements PaymentPolicy {

    private final String bankno, accountno;

    public DirectDebit(String bankno, String accountno) {
        this.bankno = bankno.replaceAll("\\D+", "");
        this.accountno = accountno.replaceAll("\\D+", "");
    }

    @Override
    public void pay(String orderId, double amount) {
        System.out.println(orderId + ": " + amount + "€ will be transferred from your account.\n");
    }

    @Override
    public String toString() {
        return "DirectDebit " + bankno + "|" + accountno;
    }
}
