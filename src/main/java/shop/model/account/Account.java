package shop.model.account;

import shop.model.mobile.MobilePhone;
import shop.model.observer.Aspect;
import shop.model.observer.Observer;
import shop.model.order.ConcreteOrder;
import shop.model.order.Order;
import shop.model.order.decorator.*;
import shop.model.payment.PaymentPolicy;
import shop.model.products.Product;

import java.util.*;

public class Account implements Observer<Aspect, String> {

    private final String first, last, phone, user, pass;
    private PaymentPolicy policy;
    private boolean loggedIn = false;
    private Order order;
    private MobilePhone mobilePhone = null;
    private final List<Order> oldOrders = new ArrayList<>(); // not working with the UI so this function is quite useless

    public Account(String first, String last) {
        this(first, last, "", (first + "." + last).toLowerCase(), "secret");
    }

    public Account(String first, String last, String phone, String user, String pass) {

        this.first = first.trim();
        this.last = last.trim();
        this.phone = phone.trim();
        this.user = user.trim().toLowerCase();
        this.pass = pass.trim();
        if (phone.length() != 0) {
            mobilePhone = new MobilePhone(phone);
            mobilePhone.dispose();
        }
        this.order = new ConcreteOrder(Arrays.asList(this, mobilePhone));
    }

    public boolean owns(String user) {
        return this.user.equals(user);
    }

    public boolean login(String user, String pass) {
        loggedIn = this.user.equals(user) && this.pass.equals(pass);
        return loggedIn;
    }

    public void logout() {
        order.logout(this);
        loggedIn = false;
    }

    public String getPhone() {
        return phone;
    }

    public void add(Product product) {
        if ((order.isEmpty() || order.isDigital()) && product.isPhysical())
            if (!(order instanceof Shipment))
                order = new Shipment(order);
        order.add(product);
    }

    public void clearOrder() {
        order = new ConcreteOrder(Arrays.asList(this, mobilePhone));
    }

    public boolean isDigitalOrder() {
        return order.isDigital();
    }

    public boolean isPhysicalOrder() {
        return order.isPhysical();
    }

    public int getCosts() {
        return order.getCosts();
    }

    public double getCostsAsEuro() {
        return order.getCosts() / 100.0;
    }

    public void setPolicy(PaymentPolicy policy) {
        this.policy = policy;
    }

    public String getPolicyAsString() {
        return policy.toString();
    }

    public void addExpress() {
        if (order.isPhysical())
            order = new Express(order);
    }

    public void addTracking() {
        order = new Tracking(order);
    }

    public void addPostBox() {
        order = new Postbox(order);
    }

    public void complete() {
        if (!loggedIn || order.isEmpty() || policy == null) return;
        order = new Deliverable(order);
        System.out.println("Complete order: " + order);
        this.pay("Order of " + new Date(), this.getCostsAsEuro());
        order.deliver();
        this.clearOrder();
    }

    private void pay(String orderId, double amount) {
        policy.pay(orderId, amount);
    }

    public void showMobile() {
        if (mobilePhone != null && !mobilePhone.isVisible()) {
            mobilePhone.pack();
            mobilePhone.setVisible(true);
        }
    }

    @Override
    public String toString() {
        return first + " " + last + "; open orders:\n" + order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        if (!Objects.equals(first, account.first)) return false;
        return Objects.equals(last, account.last);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     * <p>
     * Typically, one would switch over the aspect which has changed and perform
     * the fitting update with the help of the supplied data.
     *
     * @param aspect the aspect which has changed, this has to be an Enumeration type
     * @param data   the data which has changed
     */
    @Override
    public void update(Aspect aspect, String data) {
        System.out.println("[" + aspect.toString() + "] " + data);
    }
}
