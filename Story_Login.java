import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import n.Customer;

public class Main {
    static Map<Integer, String> users = new HashMap<>(); // Stores user id and password
    static List<Customer> customerList = new ArrayList<>(); // Stores customer details

    public static void main(String[] args) {
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
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Exiting application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(customerId) && users.get(customerId).equals(password)) {
            System.out.println("Login successful! Welcome.");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void register(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Customer Name (3-50 characters): ");
        String name = scanner.nextLine();
        if (name.length() < 3 || name.length() > 50) {
            System.out.println("Invalid name length.");
            return;
        }

        System.out.print("Enter Email (must contain '@'): ");
        String email = scanner.nextLine();
        if (!email.contains("@")) {
            System.out.println("Invalid email format.");
            return;
        }

        System.out.print("Enter Mobile Number (10 digits): ");
        String mobile = scanner.nextLine();
        if (!mobile.matches("\\d{10}")) {
            System.out.println("Invalid mobile number.");
            return;
        }

        System.out.print("Enter Password (at least one capital, one small, one digit): ");
        String password = scanner.nextLine();
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            System.out.println("Password must contain a small letter, a capital letter, and a digit.");
            return;
        }

        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        System.out.print("Enter Address (max 300 characters): ");
        String address = scanner.nextLine();
        if (address.length() > 300) {
            System.out.println("Address too long.");
            return;
        }

        // Generate Customer ID
        int customerId = new Random().nextInt(9000000) + 1000000;
        users.put(customerId, password);

        // Store Customer Data
        customerList.add(new Customer(customerId, name, email, mobile, password, address));

        System.out.println("Registration successful!");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Email: " + email);
        System.out.println("Mobile: " + mobile);
    }
}

class Customer {
    int customerId;
    String name, email, mobile, password, address;

    public Customer(int customerId, String name, String email, String mobile, String password, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.address = address;
    }
}



