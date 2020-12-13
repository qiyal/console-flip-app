package cart;

import products.Product;

public class Cart {
    private Composite cart = new Composite();

    public Cart() { }

    public void addProduct(Product product) {
        cart.addProduct(new Leaf(product));
    }

    public void removeProduct(Product product) {
        cart.removeProduct(new Leaf(product));
    }
}
