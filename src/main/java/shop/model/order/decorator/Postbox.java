package shop.model.order.decorator;

import shop.model.order.Order;

public class Postbox extends OrderDecorator {

    public Postbox(Order order) {
        super(order);
    }

    @Override
    public int getCosts() {
        return 0;
    }

    @Override
    public void delivered(String message) {
        order.delivered(message + " to Postbox");
    }

    @Override
    public String toString() {
        String string = order.toString();
        return string + "[" + Postbox.class.getSimpleName() + "]";
    }
}
