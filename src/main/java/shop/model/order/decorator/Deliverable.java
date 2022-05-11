package shop.model.order.decorator;

import shop.model.order.DeliveryService;
import shop.model.order.Order;
import shop.model.products.Product;

import java.util.List;

public class Deliverable extends OrderDecorator{

    private final DeliveryService service;

    public Deliverable(Order order) {
        super(order);
        this.service = new DeliveryService(this);
    }

    @Override
    public int getCosts() {
        return order.getCosts() + 300;
    }

    @Override
    public void deliver() {
        order.deliver();
        service.start();
    }

    @Override
    public String toString() {
        String string = order.toString();
        return string + "[" + Deliverable.class.getSimpleName() + "]";
    }
}
