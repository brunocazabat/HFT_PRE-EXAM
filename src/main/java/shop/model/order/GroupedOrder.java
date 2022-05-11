package shop.model.order;

import shop.model.observer.Aspect;
import shop.model.observer.Observer;
import shop.model.products.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupedOrder extends Order{

    public final Map<Integer, Order> orders = new HashMap<>();

    public GroupedOrder(List<Observer<Aspect, String>> observers) {
        super(observers);
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public int getCosts() {
        int orderCost = 0;
        for (int key : orders.keySet()) {
            orderCost += orders.get(key).getCosts();
        }
        return orderCost;
    }

    @Override
    public int getLatestDeliveryDate() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return orders.isEmpty();
    }

    @Override
    public boolean isPhysical() {
        for (int key : orders.keySet()) {
            if (orders.get(key).isPhysical())
                return true;
        }
        return false;
    }

    @Override
    public boolean isDigital() {
        for (int key : orders.keySet()) {
            if (orders.get(key).isDigital())
                return true;
        }
        return false;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void deliver() {

    }

    @Override
    public List<Product> deliver(int day) {
        return null;
    }

    @Override
    public void delivered(String message) {

    }
}
