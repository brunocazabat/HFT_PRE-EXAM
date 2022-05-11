package shop.model.order.decorator;

import shop.model.order.Order;
import shop.model.products.Product;

import java.util.List;

public class Express extends OrderDecorator {

    private static final int EXPRESS_DAYS = 2;

    public Express(Order order) {
        super(order);
    }

    @Override
    public int getLatestDeliveryDate() {
        int delivery = order.getLatestDeliveryDate();
        if (delivery == 1 || delivery == 2)
            delivery = 1;
        else if (delivery > 2)
            delivery = delivery - EXPRESS_DAYS;
        return delivery;
    }

    @Override
    public int getCosts() {
        return order.getCosts() + 400;
    }

    @Override
    public List<Product> deliver(int day) {
        int inclusiveDays = day;
        if (day > 0)
            inclusiveDays = day + EXPRESS_DAYS;
        return order.deliver(inclusiveDays);
    }

    @Override
    public String toString() {
        String string = order.toString();
        return string + "[" + Express.class.getSimpleName() + "]";
    }
}
