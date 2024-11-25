import java.util.*;

public class ManageCustomer {
    static Map<Integer, String> users = new HashMap<>();
    static List<Customer> customerList = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static Map<Integer, Product> products = new HashMap<>(); // Product ID -> Product details

    public static void main(String[] args) {
        loadSampleProducts(); // Load sample product data
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (login(scanner)) {
                        postLoginMenu(scanner); // Proceed to customer menu after login
                    }
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadSampleProducts() {
        products.put(101, new Product(101, "Earphone", "Electronics", 999));
        products.put(102, new Product(102, "Laptop", "Electronics", 50000));
        products.put(201, new Product(201, "Cushion Cover", "Home Decor", 299));
        products.put(301, new Product(301, "Notebook", "Stationary", 49));
        // Add more products as needed
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

       
            System.out.println("Login successful! Welcome.");
            return true;
        
    }

    private static void postLoginMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1) View Products");
            System.out.println("2) Payment");
            System.out.println("3) Previous Orders");
            System.out.println("4) View Profile");
            System.out.println("5) Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProducts(scanner);
                    break;
                case 2:
                    makePayment(scanner);
                    break;
                case 3:
                    viewPreviousOrders();
                    break;
                case 4:
                    viewProfile(scanner);
                    break;
                case 5:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewProducts(Scanner scanner) {
        System.out.println("\nProduct Categories:");
        System.out.println("1) Electronics");
        System.out.println("2) Home Decor");
        System.out.println("3) Stationary");
        System.out.print("Select a category: ");
        int categoryChoice = scanner.nextInt();

        System.out.println("\nProducts:");
        for (Product product : products.values()) {
            if ((categoryChoice == 1 && product.category.equals("Electronics")) ||
                (categoryChoice == 2 && product.category.equals("Home Decor")) ||
                (categoryChoice == 3 && product.category.equals("Stationary"))) {
                System.out.println(product.productId + ". " + product.name + " - ₹" + product.price);
            }
        }
        System.out.print("Enter Product ID to purchase (or 0 to cancel): ");
        int productId = scanner.nextInt();
        if (products.containsKey(productId)) {
            Product selectedProduct = products.get(productId);
            System.out.println("You selected: " + selectedProduct.name + " - ₹" + selectedProduct.price);
            orders.add(new Order(selectedProduct));
            System.out.println("Added to cart. Proceed to payment.");
        } else if (productId != 0) {
            System.out.println("Invalid Product ID.");
        }
    }

    private static void makePayment(Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("No items to purchase.");
            return;
        }
        Order latestOrder = orders.get(orders.size() - 1);
        System.out.println("Amount to be paid: ₹" + latestOrder.amount);
        System.out.print("Enter Card No (16 digits): ");
        String cardNo = scanner.next();
        if (!cardNo.matches("\\d{16}")) {
            System.out.println("Invalid card number.");
            return;
        }

        scanner.nextLine(); // Consume newline
        System.out.print("Enter Card Holder Name: ");
        String cardHolderName = scanner.nextLine();
        if (cardHolderName.length() < 10) {
            System.out.println("Card holder name must be at least 10 characters.");
            return;
        }

        System.out.print("Enter Expiry Date (MM/YY): ");
        String expiryDate = scanner.next();
        System.out.print("Enter CVV (3 digits): ");
        String cvv = scanner.next();
        if (!cvv.matches("\\d{3}")) {
            System.out.println("Invalid CVV.");
            return;
        }

        String orderId = String.format("%012d", new Random().nextInt(1_000_000_000));
        System.out.println("Payment successful!");
        System.out.println("Order ID: " + orderId);
        System.out.println("Amount Paid: ₹" + latestOrder.amount);
        latestOrder.orderId = orderId;
    }

    private static void viewPreviousOrders() {
        if (orders.isEmpty()) {
            System.out.println("No previous orders found.");
        } else {
            for (Order order : orders) {
                System.out.println("Order ID: " + order.orderId + ", Product: " + order.product.name + ", Amount: ₹" + order.amount);
            }
        }
    }

    private static void viewProfile(Scanner scanner) {
        System.out.println("Customer Profile:");
        // For simplicity, display a dummy profile. Implement user-specific profile logic if needed.
        System.out.println("Customer ID: 1234567");
        System.out.println("Name: John Doe");
        System.out.println("Email: john@example.com");

        System.out.print("Update password? (yes/no): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            System.out.println("Password updated successfully!");
        }
    }
}

class Product {
    int productId;
    String name, category;
    int price;

    public Product(int productId, String name, String category, int price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}

class Order {
    String orderId;
    Product product;
    int amount;

    public Order(Product product) {
        this.product = product;
        this.amount = product.price;
    }
}
