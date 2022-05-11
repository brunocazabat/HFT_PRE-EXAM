package shop.model.order.decorator;

import shop.model.order.Order;

public class Shipment extends OrderDecorator{

    public Shipment(Order order) {
        super(order);
    }

    @Override
    public int getCosts() {
        return order.getCosts() + 300;
    }

    @Override
    public String toString() {
        String string = order.toString();
        return string + "[" + Shipment.class.getSimpleName() + "]";
    }
}
