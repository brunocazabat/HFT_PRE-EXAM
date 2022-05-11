package shop.model.order;

import shop.model.observer.Aspect;
import shop.model.observer.Observable;
import shop.model.observer.Observer;
import shop.model.products.Product;

import java.util.List;

public abstract class Order implements Observable<Aspect, String> {

    public List<Observer<Aspect, String>> observers;

    public Order(List<Observer<Aspect, String>> observers) {
        this.observers = observers;
        for (Observer<Aspect, String> obs : observers) {
            addObserver(obs);
        }
    }

    public void logout(Observer<Aspect, String> observer){
        // observers.remove(observer);
        deleteObserver(observer);
    }

    public abstract List<Product> getProducts();

    public abstract int getCosts();

    public abstract int getLatestDeliveryDate();

    public abstract boolean isEmpty();

    public abstract boolean isPhysical();

    public abstract boolean isDigital();

    public abstract void add(Product product);

    public abstract void deliver();

    public abstract List<Product> deliver(int day);

    public abstract void delivered(String message);

    protected String listing(String message, String bullet, List<Product> products) {
        if (products == null || products.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(message).append('\n');

        for (Product product : products)
            sb.append(bullet).append(' ').append(product.toString()).append('\n');
        return sb.toString();
    }
}
