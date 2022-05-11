package shop.model.products;

import an.additional.set.of.products.Article;

public class CommissionedProduct implements Product {

    private final Article article;
    private final int price;

    public CommissionedProduct(Article article) {
        this.article = article;
        double priceWithTaxes = article.getPriceWithoutTaxes() * 1.19;
        this.price = (int) (priceWithTaxes * 100);
    }

    @Override
    public String getName() {
        return article.getDescription();
    }

    @Override
    public int getDeliveryPeriod() {
        return 10;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isDigital() {
        return !this.isPhysical();
    }

    @Override
    public boolean isPhysical() {
        return true;
    }

    @Override
    public String toString() {
        return (getName().length() < 20 ? getName() : getName().substring(0, 20) + "...")
                + ", (Commissioned, " + toEuro() + "€)";
    }

    private double toEuro() {
        return price / 100.0;
    }
}
