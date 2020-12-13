package cart;

import products.Product;

public class Cart {
    private Composite cart = new Composite();

    public Cart() { }

    public int getTotalCost() {
        return cart.calculateTotalCost();
    }

    public void addProduct(Product product) {
        cart.addProduct(new Leaf(product));
    }

    public void removeProduct(int i) {
        cart.removeProduct(i);
    }

    public void show() {
        cart.show();
    }

    public int getSize() {
        return cart.getSize();
    }

    public void clear() {
        cart.clear();
    }
}
