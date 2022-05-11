package shop.model.order;

import shop.model.observer.Aspect;
import shop.model.observer.Observer;
import shop.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ConcreteOrder extends Order {

    private final List<Product> products = new ArrayList<>();

    public ConcreteOrder(List<Observer<Aspect, String>> observers) {
        super(observers);
    }

    // === INFO OPERATIONS =============================================================================================

    public int getCosts() {
        int costs = 0;
        for (Product p : products)
            costs = costs + p.getPrice();
        return costs;
    }

    public int getLatestDeliveryDate() {
        int delivery = 0;
        for (Product p : products)
            if (p.getDeliveryPeriod() > delivery)
                delivery = p.getDeliveryPeriod();
        return delivery;
    }

    public String toString() {
        String string = products.toString();
        return string.substring(1, string.length() - 1);
    }

    // === DELIVERY OPERATIONS =========================================================================================

    public void deliver() {
        notifyObservers(Aspect.NOTIFICATION, listing("Sent out:", "-", products));
    }

    public List<Product> deliver(int day) {
        List<Product> delivered = new ArrayList<>();
        for (Product p : products)
            if (p.getDeliveryPeriod() <= day)
                delivered.add(p);
        products.removeAll(delivered);

        return delivered;
    }

    public void delivered(String message) {
        notifyObservers(Aspect.NOTIFICATION, "Everything delivered" + message);
    }

    // === PRODUCT OPERATIONS ==========================================================================================

    public List<Product> getProducts() {
        return products;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public boolean isPhysical() {
        if (products.isEmpty())
            return false;
        for (Product p : products)
            if (p.isPhysical())
                return true;
        return false;
    }

    public boolean isDigital() {
        if (products.isEmpty())
            return false;
        for (Product p : products)
            if (p.isPhysical())
                return false;
        return true;
    }

    public void add(Product product) {
        this.products.add(product);
    }
}
