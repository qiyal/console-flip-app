package cart;

import databases.ProductDatabase;
import products.Product;

import java.util.ArrayList;

public class Composite implements Component {
    ArrayList<Component> box = new ArrayList<>();
    ProductDatabase productDatabase = ProductDatabase.getInstance();

    @Override
    public int calculateTotalCost() {
        int totalCost = 0;

        for (Component component : box) {
            totalCost += component.calculateTotalCost();
        }
        return totalCost;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void show() {
        showItems();
    }

    public void addProduct(Component component) {
        System.out.println("is add");
        box.add(component);
    }

    public void removeProduct(int i) {
        Product product = ((Leaf) box.get(i)).product;
        box.remove(i);
        productDatabase.incrementQuantity(product);
    }

    public void clear() {
        box.clear();
    }

    public void showItems() {
        System.out.println("\n[ Cart Items ]");

        for (int i = 0; i < box.size(); i++) {
            System.out.print("\n" + i + ") ");
            box.get(i).show();
            System.out.println();
        }
    }

    public int getSize() {
        return box.size();
    }

}
