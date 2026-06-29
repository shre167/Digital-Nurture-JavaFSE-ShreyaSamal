import java.util.Arrays;

class Order {
    private final String orderId;
    private final String customerName;
    private final double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return String.format("Order{id=%s, customer=%s, total=%.2f}", orderId, customerName, totalPrice);
    }
}

public class OrderSorting {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void quickSort(Order[] orders) {
        quickSort(orders, 0, orders.length - 1);
    }

    private static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivotValue = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivotValue) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = new Order[] {
            new Order("O1001", "Alice", 49.99),
            new Order("O1002", "Bob", 299.95),
            new Order("O1003", "Cathy", 149.50),
            new Order("O1004", "David", 19.99)
        };

        Order[] bubbleOrders = Arrays.copyOf(orders, orders.length);
        bubbleSort(bubbleOrders);
        System.out.println("Bubble sort result:");
        Arrays.stream(bubbleOrders).forEach(System.out::println);

        Order[] quickOrders = Arrays.copyOf(orders, orders.length);
        quickSort(quickOrders);
        System.out.println("\nQuick sort result:");
        Arrays.stream(quickOrders).forEach(System.out::println);
    }
}
