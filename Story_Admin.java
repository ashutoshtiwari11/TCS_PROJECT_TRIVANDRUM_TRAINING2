import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class AdminMenu {
    static List<Product> productList = new ArrayList<>(); // Store products
    static List<Customer> customerList = new ArrayList<>(); // Store customers
    static List<Order> orderList = new ArrayList<>(); // Store orders
    static int productIdCounter = 100; // To auto-generate unique product IDs

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1) Add Product");
            System.out.println("2) View All Products");
            System.out.println("3) Update a Product");
            System.out.println("4) Delete a Product");
            System.out.println("5) Search Product by ID");
            System.out.println("6) View All Customers");
            System.out.println("7) View All Orders");
            System.out.println("8) Search Order by ID");
            System.out.println("9) Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProductById(scanner);
                    break;
                case 6:
                    viewAllCustomers();
                    break;
                case 7:
                    viewAllOrders();
                    break;
                case 8:
                    searchOrderById(scanner);
                    break;
                case 9:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Product Name (Max 50 characters): ");
        String name = scanner.nextLine();
        if (name.length() > 50) {
            System.out.println("Product name exceeds 50 characters. Try again.");
            return;
        }

        System.out.print("Enter Product Price: ");
        float price = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Product Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Product Description: ");
        String description = scanner.nextLine();

        Product newProduct = new Product(productIdCounter++, name, category, description, price);
        productList.add(newProduct);

        System.out.println("Product added successfully!");
        System.out.println("Product ID: " + newProduct.productId);
        System.out.println("Product Name: " + newProduct.name);
        System.out.println("Description: " + newProduct.description);
    }

    private static void viewAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("\nAll Products:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Product product : productList) {
            if (product.productId == productId) {
                System.out.println("Product found: " + product);
                System.out.print("Enter new name (press enter to skip): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) product.name = newName;

                System.out.print("Enter new price (press enter to skip): ");
                String newPrice = scanner.nextLine();
                if (!newPrice.isEmpty()) product.price = Float.parseFloat(newPrice);

                System.out.print("Enter new category (press enter to skip): ");
                String newCategory = scanner.nextLine();
                if (!newCategory.isEmpty()) product.category = newCategory;

                System.out.print("Enter new description (press enter to skip): ");
                String newDescription = scanner.nextLine();
                if (!newDescription.isEmpty()) product.description = newDescription;

                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product ID not found.");
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Enter Product ID to delete: ");
        int productId = scanner.nextInt();

        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.productId == productId) {
                iterator.remove();
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        System.out.println("Product ID not found.");
    }

    private static void searchProductById(Scanner scanner) {
        System.out.print("Enter Product ID to search: ");
        int productId = scanner.nextInt();

        for (Product product : productList) {
            if (product.productId == productId) {
                System.out.println("Product found: " + product);
                return;
            }
        }
        System.out.println("Product ID not found.");
    }

    private static void viewAllCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("\nAll Customers:");
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    private static void viewAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        System.out.println("\nAll Orders:");
        for (Order order : orderList) {
            System.out.println(order);
        }
    }

    private static void searchOrderById(Scanner scanner) {
        System.out.print("Enter Order ID to search: ");
        String orderId = scanner.next();

        for (Order order : orderList) {
            if (order.orderId.equals(orderId)) {
                System.out.println("Order found: " + order);
                return;
            }
        }
        System.out.println("Order ID not found.");
    }
}

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
        return "ID: " + productId + ", Name: " + name + ", Category: " + category + ", Price: ₹" + price + ", Description: " + description;
    }
}

class Customer {
    int customerId;
    String name, email;

    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + customerId + ", Name: " + name + ", Email: " + email;
    }
}

class Order {
    String orderId;
    Product product;
    int amount;

    public Order(String orderId, Product product) {
        this.orderId = orderId;
        this.product = product;
        this.amount = (int) product.price;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Product: " + product.name + ", Amount: ₹" + amount;
    }
}
