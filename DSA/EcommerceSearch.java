public class EcommerceSearch {
    static class Product {
        private final String productId;
        private final String productName;
        private final String category;

        public Product(String productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        public String getTitle() {
            return productName;
        }

        @Override
        public String toString() {
            return String.format("Product{id=%s, name=%s, category=%s}", productId, productName, category);
        }
    }

    public static Product linearSearch(Product[] products, String query) {
        for (Product product : products) {
            if (product.getTitle().equalsIgnoreCase(query)) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] sortedProducts, String query) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = sortedProducts[mid].getTitle().compareToIgnoreCase(query);
            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = new Product[] {
            new Product("P001", "Camera", "Electronics"),
            new Product("P002", "Headphones", "Electronics"),
            new Product("P003", "Notebook", "Stationery"),
            new Product("P004", "Smartphone", "Electronics"),
            new Product("P005", "Water Bottle", "Home")
        };

        System.out.println("Linear search result:");
        System.out.println(linearSearch(products, "Smartphone"));

        java.util.Arrays.sort(products, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
        System.out.println("\nBinary search result:");
        System.out.println(binarySearch(products, "Smartphone"));
    }
}
