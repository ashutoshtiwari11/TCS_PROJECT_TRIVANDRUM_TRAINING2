package any;
import java.util.*;

public class ManageProducts {
    static List<Product> productList = new ArrayList<>();
    static List<Customer> customerList = new ArrayList<>();
    static List<Order> orderList = new ArrayList<>();
    static int productIdCounter = 100; // Auto-generated unique Product ID

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1) View All Products");
            System.out.println("2) Update a Product");
            System.out.println("3) Delete a Product");
            System.out.println("4) View All Customers");
            System.out.println("5) View All Orders");
            System.out.println("6) Search Product by ID");
            System.out.println("7) Search Order by ID");
            System.out.println("8) Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    updateProduct(scanner);
                    break;
                case 3:
                    deleteProduct(scanner);
                    break;
                case 4:
                    viewAllCustomers();
                    break;
                case 5:
                    viewAllOrders();
                    break;
                case 6:
                    searchProductById(scanner);
                    break;
                case 7:
                    searchOrderById(scanner);
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // **View All Products**
    private static void viewAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.printf("%-10s | %-20s | %-10s | %-15s | %-30s%n",
                "Product ID", "Product Name", "Price", "Category", "Description");
        for (Product product : productList) {
            System.out.printf("%-10d | %-20s | %-10.2f | %-15s | %-30s%n",
                    product.productId, product.name, product.price, product.category, product.description);
        }
    }

    // **Update Product**
    private static void updateProduct(Scanner scanner) {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Product product : productList) {
            if (product.productId == productId) {
                System.out.println("Product found: " + product);

                System.out.print("Enter new name (press Enter to skip): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) product.name = newName;

                System.out.print("Enter new price (press Enter to skip): ");
                String newPrice = scanner.nextLine();
                if (!newPrice.isEmpty()) product.price = Float.parseFloat(newPrice);

                System.out.print("Enter new category (press Enter to skip): ");
                String newCategory = scanner.nextLine();
                if (!newCategory.isEmpty()) product.category = newCategory;

                System.out.print("Enter new description (press Enter to skip): ");
                String newDescription = scanner.nextLine();
                if (!newDescription.isEmpty()) product.description = newDescription;

                System.out.println("Product details updated successfully!");
                System.out.println("Updated Product: " + product);
                return;
            }
        }
        System.out.println("Product ID not found.");
    }

    // **Delete Product**
    private static void deleteProduct(Scanner scanner) {
        System.out.print("Enter Product ID to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.productId == productId) {
                iterator.remove();
                System.out.println("Product details are deleted successfully!");
                return;
            }
        }
        System.out.println("Product ID not found.");
    }

    // **View All Customers**
    private static void viewAllCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.printf("%-10s | %-20s | %-20s | %-15s | %-30s%n",
                "Customer ID", "Customer Name", "Email", "Mobile", "Address");
        for (Customer customer : customerList) {
            System.out.printf("%-10d | %-20s | %-20s | %-15s | %-30s%n",
                    customer.customerId, customer.name, customer.email, customer.mobile, customer.address);
        }
    }

    // **View All Orders**
    private static void viewAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        System.out.printf("%-10s | %-20s | %-20s | %-15s | %-30s%n",
                "Order ID", "Customer Name", "Email", "Mobile", "Address");
        for (Order order : orderList) {
            System.out.printf("%-10s | %-20s | %-20s | %-15s | %-30s%n",
                    order.orderId, order.customer.name, order.customer.email, order.customer.mobile, order.customer.address);
        }
    }

    // **Search Product by ID**
    private static void searchProductById(Scanner scanner) {
        System.out.print("Enter Product ID to search: ");
        int productId = scanner.nextInt();
        for (Product product : productList) {
            if (product.productId == productId) {
                System.out.println("Product found:");
                System.out.printf("%-10d | %-20s | %-10.2f | %-15s | %-30s%n",
                        product.productId, product.name, product.price, product.category, product.description);
                return;
            }
        }
        System.out.println("Product ID not found.");
    }

    // **Search Order by ID**
    private static void searchOrderById(Scanner scanner) {
        System.out.print("Enter Order ID to search: ");
        String orderId = scanner.next();
        for (Order order : orderList) {
            if (order.orderId.equals(orderId)) {
                System.out.println("Order found:");
                System.out.printf("%-10s | %-20s | %-15s | %-20s | %-30s%n",
                        order.orderId, order.customer.name, order.customer.mobile, order.customer.email, order.customer.address);
                return;
            }
        }
        System.out.println("Order ID not found.");
    }
}

// Supporting Classes
class Product {
    int productId;
    String name, category, description;
    float price;

    public Product(int productId, String name, String category, String description, float price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + productId + ", Name: " + name + ", Price: ₹" + price + ", Category: " + category + ", Description: " + description;
    }
}

class Customer {
    int customerId;
    String name, email, mobile, address;

    public Customer(int customerId, String name, String email, String mobile, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }
}

class Order {
    String orderId;
    Customer customer;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }
}
