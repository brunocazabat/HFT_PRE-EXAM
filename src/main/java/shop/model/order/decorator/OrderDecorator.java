package shop.model.order.decorator;

import shop.model.order.Order;
import shop.model.products.Product;

import java.util.List;

public abstract class OrderDecorator extends Order {

    protected final Order order;

    protected OrderDecorator(Order order) {
        super(order.observers);
        this.order = order;
    }

    @Override
    public int getCosts() {
        return order.getCosts();
    }

    @Override
    public boolean isEmpty() {
        return order.isEmpty();
    }

    @Override
    public boolean isPhysical() {
        return order.isPhysical();
    }

    @Override
    public boolean isDigital() {
        return order.isDigital();
    }

    @Override
    public void add(Product product) {
        order.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return order.getProducts();
    }

    @Override
    public void deliver() {
        order.deliver();
    }

    @Override
    public List<Product> deliver(int day) {
        return order.deliver(day);
    }

    @Override
    public void delivered(String message) {
        order.delivered(message);
    }

    @Override
    public int getLatestDeliveryDate() {
        return order.getLatestDeliveryDate();
    }
}
