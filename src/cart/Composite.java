package cart;

import java.util.ArrayList;

public class Composite implements Component {
    ArrayList<Component> box = new ArrayList<>();

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

    public void addProduct(Component component) {
        box.add(component);
    }

    public void removeProduct(Component component) {
        box.remove(component);
    }

    public void clear() {
        box.clear();
    }

}
