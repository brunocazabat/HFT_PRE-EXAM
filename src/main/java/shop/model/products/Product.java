package shop.model.products;

public interface Product {

    String getName();

    int getDeliveryPeriod();

    int getPrice();

    boolean isDigital();

    boolean isPhysical();

    String toString();
}
