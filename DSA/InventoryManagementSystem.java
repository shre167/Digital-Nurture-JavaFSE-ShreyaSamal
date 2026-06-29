import java.util.HashMap;
import java.util.Map;

class InventoryItem {
    private final String productId;
    private String productName;
    private int quantity;
    private double price;

    public InventoryItem(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("InventoryItem{id=%s, name=%s, qty=%d, price=%.2f}", productId, productName, quantity, price);
    }
}

public class InventoryManagementSystem {
    private final Map<String, InventoryItem> inventory = new HashMap<>();

    public boolean addProduct(InventoryItem item) {
        if (item == null || inventory.containsKey(item.getProductId())) {
            return false;
        }
        inventory.put(item.getProductId(), item);
        return true;
    }

    public boolean updateProduct(String productId, String name, Integer quantity, Double price) {
        InventoryItem existing = inventory.get(productId);
        if (existing == null) {
            return false;
        }
        if (name != null) {
            existing.setProductName(name);
        }
        if (quantity != null) {
            existing.setQuantity(quantity);
        }
        if (price != null) {
            existing.setPrice(price);
        }
        return true;
    }

    public boolean deleteProduct(String productId) {
        return inventory.remove(productId) != null;
    }

    public InventoryItem getProduct(String productId) {
        return inventory.get(productId);
    }

    public void printInventory() {
        inventory.values().forEach(System.out::println);
    }

    public static void main(String[] args) {
        InventoryManagementSystem manager = new InventoryManagementSystem();

        manager.addProduct(new InventoryItem("P001", "Widget", 100, 4.99));
        manager.addProduct(new InventoryItem("P002", "Gadget", 75, 9.99));
        manager.addProduct(new InventoryItem("P003", "Thingamajig", 50, 14.49));

        System.out.println("Initial inventory:");
        manager.printInventory();

        manager.updateProduct("P002", "Gadget Pro", 80, 11.99);
        System.out.println("\nAfter update:");
        System.out.println(manager.getProduct("P002"));

        manager.deleteProduct("P003");
        System.out.println("\nAfter delete:");
        manager.printInventory();
    }
}
