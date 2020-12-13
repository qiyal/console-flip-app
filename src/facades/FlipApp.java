package facades;

import cart.Cart;
import databases.ProductDatabase;
import databases.UserDatabase;
import products.Product;
import services.AuthService;
import services.Role;

import java.util.ArrayList;
import java.util.Scanner;

public class FlipApp {
    private UserDatabase userDatabase;
    private ProductDatabase productDatabase;
    private AuthService authService;
    private Cart cart;
    private int op;
    private int chose;
    private Scanner sc;

    public void initDatabases() {
        userDatabase = UserDatabase.getInstance();
        productDatabase = ProductDatabase.getInstance();
    }

    public void initServices() {
        authService = AuthService.getInstance();
    }

    public void initSystem() {
        initDatabases();
        initServices();
        cart = new Cart();
        sc = new Scanner(System.in);
    }

    public void run() {
        initSystem();

        while(true) {
            if (authService.isAuth()) {
                if (authService.getAuthUserRole() == Role.ADMIN) {
                    openAdminWindow();
                } else {
                    System.out.println("\nenter 1 - add a product to Cart");
                    System.out.println("enter 2 - remove a product from the Cart");
                    System.out.println("enter 0 - EXIT");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    if (op == 1) {
                        actionAddToCart();
                    } else if (op == 2) {

                    } else if (op == 0) {
                        showExitMessage();
                    } else {
                        showErrorInvalidArgument();
                    }
                }
            } else {
                System.out.println("\nenter 1 - Sign In");
                System.out.println("enter 2 - Sign Up");
                System.out.println("enter 0 - EXIT");
                System.out.print("enter: ");
                op = sc.nextInt();

                if (op == 1) {
                    signIn();
                } else if (op == 2) {
                    signUp();
                } else if (op == 0) {
                    showExitMessage();
                    break;
                } else {
                    showErrorInvalidArgument();
                }

            }
        }
    }

    private void signUp() {
        System.out.println("\nenter 1 - Client");
        System.out.println("enter 2 - Admin");
        System.out.print("enter: ");
        op = sc.nextInt();

        if (op == 1) {

        } else if (op == 2) {

        } else {
            showExitMessage();
        }
    }

    private void createClient() {
        String firstName, lastname, email, password;

        System.out.println("\nFirst Name: ");
        firstName = sc.next();
        System.out.println("\nLast Name: ");
        lastname = sc.next();
    }

    private void signIn() {
        String login, password;

        System.out.print("\nLogin: ");
        login = sc.next();

        System.out.print("Password: ");
        password = sc.next();

        if (authService.login(login, password)) {
            System.out.println("\n[ Welcome " + authService.getAuthUserLogin() + " ! ]");
        } else {
            System.out.println("\nLogin or password is not correct!");
        }
    }

    private void actionAddToCart() {
        System.out.println("\n[ Products ]");

        ArrayList<Product> products = productDatabase.getAllProducts();

        String str;
        for (int i = 0; i < products.size(); i++) {
            str = (products.get(i).getQuantity() > 0) ? "in stock" : "out of stock";
            System.out.println("\n" + i +  ") " + products.get(i).showDetails() + "\nStatus: " + str);
        }

        System.out.print("enter: ");
        chose = sc.nextInt();

        if (products.size() > chose) {
            cart.addProduct(products.get(chose));
            productDatabase.decrementQuantity(products.get(chose));
            System.out.println("\nAdded.");
        } else {
            showErrorInvalidArgument();
        }
    }

    private void openAdminWindow() {
    }

    public void showErrorInvalidArgument() {
        System.out.println("\nInvalid argument!");
    }

    public void showExitMessage() {
        System.out.println("\n--- EXIT APP ---");
    }
}
