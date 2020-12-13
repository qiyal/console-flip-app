package cart;

import products.Product;

public class Leaf implements Component {
    Product product;

    public Leaf(Product product) {
        this.product = product;
    }

    @Override
    public int calculateTotalCost() {
        return product.getPrice();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void show() {
        System.out.println(product.showDetails());
    }
}
