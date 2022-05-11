package shop.model.main;

import an.additional.set.of.products.Article;
import shop.model.account.Account;
import shop.model.payment.DirectDebit;
import shop.model.products.CommissionedProduct;
import shop.model.products.StockedProduct;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Create the account
        Account account = new Account("John", "Doe", "555 555 123", "john.doe", "secret");
        account.login("john.doe", "secret");

        account.setPolicy(new DirectDebit("500600", "12345678"));
        // or
        // account.setPolicy(new PayPal("john.doe@gmail.com"));

        // Create and add some products
        account.add(new StockedProduct("download", "Java eBook", 9.99, 0));
        account.add(new StockedProduct("CD", "Some Music", 4.99, 1));
        account.add(new StockedProduct("BluRay", "A Movie", 8.99, 2));
        account.add(new StockedProduct("BluRay", "A Second Movie", 8.99, 2));
        account.add(new StockedProduct("Book", "Java Book", 6.99, 3));
        account.add(new StockedProduct("DVD", "Another Movie", 8.99, 4));
        account.add(new StockedProduct("CD", "Some Rare Music", 10.99, 5));
        account.add(new StockedProduct("Book", "Some Rare Book", 10.99, 6));

        // Create and add some articles
        Article article = new Article("Some Hardware Device", 10.00);
        account.add(new CommissionedProduct(article));

        // Add options
        account.addExpress();
        account.addTracking();
        account.addPostBox();

        // Complete the order
        account.showMobile();
        account.complete();

        // logout after some time
        Thread.sleep(10000);
        account.logout();
        System.out.println("logged out");
    }
}
