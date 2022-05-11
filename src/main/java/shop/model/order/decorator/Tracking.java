package shop.model.order.decorator;

import shop.model.observer.Aspect;
import shop.model.order.Order;
import shop.model.products.Product;

import java.util.List;

public class Tracking extends OrderDecorator {

    public Tracking(Order order) {
        super(order);
    }

    @Override
    public int getCosts() {
        return order.getCosts() + 200;
    }

    @Override
    public void deliver() {
        order.deliver();
        notifyObservers(Aspect.NOTIFICATION, "Tracking started\n");
    }

    @Override
    public List<Product> deliver(int day) {
        List<Product> delivered = order.deliver(day);
        if (!delivered.isEmpty())
            notifyObservers(Aspect.NOTIFICATION, listing("Day " + day + ", delivered:", "*", delivered));

        List<Product> remaining = order.getProducts();
        if (!remaining.isEmpty())
            notifyObservers(Aspect.NOTIFICATION, listing("Day " + day + ", still on its way:", "-", remaining));
        return delivered;
    }

    @Override
    public void delivered(String message) {
        notifyObservers(Aspect.NOTIFICATION, "Tracking finished\n");
        order.delivered(message);
    }

    @Override
    public String toString() {
        String string = order.toString();
        return string + "[" + Tracking.class.getSimpleName() + "]";
    }
}
