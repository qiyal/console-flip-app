package facades;

import builders.ComicsBuilder;
import builders.director.Director;
import cart.Cart;
import databases.OrderDatabase;
import databases.ProductDatabase;
import databases.UserDatabase;
import decorators.NotifierEnum;
import listeners.EventManeger;
import listeners.InStockListener;
import listeners.NewEpisodeComics;
import listeners.NewSalesListener;
import orders.Order;
import products.Book;
import products.Comics;
import products.Product;
import services.AuthService;
import services.Role;
import strategies.PayByCash;
import strategies.PayByCreditCard;
import strategies.PayByInstalments;
import strategies.PayStrategy;
import users.Admin;
import users.Client;

import java.util.ArrayList;
import java.util.Scanner;

public class FlipApp {
    private UserDatabase userDatabase;
    private ProductDatabase productDatabase;
    private OrderDatabase orderDatabase;
    private AuthService authService;
    private EventManeger eventManeger;
    private Cart cart;
    private int op;
    private int chose;
    private Scanner sc;

    private void initDatabases() {
        userDatabase = UserDatabase.getInstance();
        productDatabase = ProductDatabase.getInstance();
        orderDatabase = OrderDatabase.getInstance();
    }

    private void initServices() {
        authService = AuthService.getInstance();
    }

    private void initSystem() {
        initDatabases();
        initServices();
        eventManeger = new EventManeger();
        cart = new Cart();
        sc = new Scanner(System.in);
    }

    public void run() {
        initSystem();

        while(true) {
            if (authService.isAuth()) {
                if (authService.getAuthUserRole() == Role.ADMIN) {
                    System.out.println("\nenter 1 - add a new Sale");
                    System.out.println("enter 2 - add new episode of comics or book");
                    System.out.println("enter 3 - do new party of product");
                    System.out.println("enter 4 - use builder");
                    System.out.println("enter 5 - show orders");
                    System.out.println("enter -1 - SING OUT");
                    System.out.println("enter 0 - EXIT");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    if (op == 1) {
                        addNewSale();
                    } else if (op == 2) {
                        addNewEpisode();
                    } else if (op == 3) {
                        addIncrementQuantity();
                    } else if (op == 4) {
                        useBilder();
                    } else if (op == 5) {
                        System.out.println();
                        for (Order order : orderDatabase.getAllOrders()) {
                            order.showInfo();
                            System.out.println();
                        }
                    }
                    else if (op == -1) {
                        authService.logout();
                    } else if (op == 0) {
                        showExitMessage();
                        break;
                    } else {
                        showErrorInvalidArgument();
                    }
                } else {
                    System.out.println("\nenter 1 - add a product to Cart");
                    System.out.println("enter 2 - remove a product from the Cart");
                    System.out.println("enter 3 - show Cart");
                    System.out.println("enter 4 - place your order");
                    System.out.println("enter 5 - add notifier");
                    System.out.println("enter 6 - delete notifier");
                    System.out.println("enter 7 - subscribe");
                    System.out.println("enter 8 - unsubscribe");
                    System.out.println("enter -1 - SING OUT");
                    System.out.println("enter 0 - EXIT");
                    System.out.print("enter: ");
                    op = sc.nextInt();

                    if (op == 1) {
                        actionAddToCart();
                    } else if (op == 2) {
                        actionRemoveItem();
                    } else if (op == 3) {
                        cart.show();
                    } else if (op == 4) {
                        pay();
                    } else if (op == 5) {
                        addNotifier();
                    } else if (op == 6) {
                        deleteNotifier();
                    } else if (op == 7) {
                        subscribe();
                    } else if (op == 8) {
                        unSubs();
                    } else if (op == -1) {
                        authService.logout();

                    } else if (op == 0) {
                        showExitMessage();
                        break;
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

    private void useBilder() {
        Director director = new Director();
        System.out.println("enter 1 - buildFullmetalAlchemistComics");
        System.out.println("enter 2 - buildMajorThunder");
        System.out.println("enter: ");
        op = sc.nextInt();

        if (op == 1) {
            productDatabase.addProduct(director.buildFullmetalAlchemistComics(new ComicsBuilder()));
            String messaga = productDatabase.getProductById(11).getName() + ". New Episode, already on sale!";
            eventManeger.notifyUsers(Integer.toString(11), "new-episode", "");
        } else {
            String messaga = productDatabase.getProductById(13).getName() + ". New Episode, already on sale!";
            eventManeger.notifyUsers(Integer.toString(13), "new-episode", "");
            productDatabase.addProduct(director.buildMajorThunder(new ComicsBuilder()));
        }
        System.out.println("\nAdded!");
    }

    private void addIncrementQuantity() {
        ArrayList<Product> products = new ArrayList<>(productDatabase.getAllProducts());

        System.out.println("[ Products ]");
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getQuantity() == 0) {
                System.out.println("\n" + i +  ") " + products.get(i).showDetails());
            }
        }
        System.out.print("enter: ");
        chose = sc.nextInt();

        String messaga = products.get(chose).getName() + ", now in stock!";
        String id = Integer.toString(products.get(chose).getId());
        eventManeger.notifyUsers(id, "in-stock", messaga);
    }

    private void addNewEpisode() {
        ArrayList<Product> products = new ArrayList<>(productDatabase.getAllProducts());

        System.out.println("[ Products ]");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("\n" + i +  ") " + products.get(i).showDetails());
        }
        System.out.print("enter: ");
        chose = sc.nextInt();

        String messaga = products.get(chose).getName() + ". New Episode, already on sale!";
        String id = Integer.toString(products.get(chose).getId());
        eventManeger.notifyUsers(id, "new-episode", messaga);

        if (products.get(chose) instanceof Comics) {
            Comics newComics = (Comics) products.get(chose);
            newComics.setComicsId(newComics.comicsId + 1);
            newComics.setName(newComics.name + "New Episode");
            productDatabase.addProduct(newComics);
        } else {
            Book book = (Book) products.get(chose);
            book.setBookId(book.getId() + 1);
            book.setName(book.getName() + "New Episode");
            productDatabase.addProduct(book);
        }


    }

    private void addNewSale() {
        System.out.println("\nNew Sale Text: ");
        System.out.print("enter: ");
        String text = sc.next();

        eventManeger.notifyUsers("-", "new-sale", text);
    }

    private void unSubs() {
        System.out.println("enter 1 - In stock");
        System.out.println("enter 2 - New episode");
        System.out.println("enter 3 - New Sale");
        System.out.print("enter: ");
        op = sc.nextInt();

        ArrayList<Product> products = new ArrayList<>(productDatabase.getAllProducts());

        if (op == 1) {
            System.out.println("[ Products ]");
            for (int i = 0; i < products.size(); i++) {
                System.out.println("\n" + i +  ") " + products.get(i).showDetails());
            }
            System.out.print("enter: ");
            chose = sc.nextInt();

            String email = ((Client)(userDatabase.getUserByLogin(this.authService.getAuthUserLogin()))).getEmail();
            String id = Integer.toString(products.get(chose).getId());

            InStockListener inStockListener = new InStockListener(id, email);

            eventManeger.unsubscribe("in-stock", inStockListener);

        } else if (op == 2) {
            System.out.println("[ Products ]");
            for (int i = 0; i < products.size(); i++) {
                System.out.println("\n" + i +  ") " + products.get(i).showDetails());
            }
            System.out.print("enter: ");
            chose = sc.nextInt();

            String email = ((Client)(userDatabase.getUserByLogin(this.authService.getAuthUserLogin()))).getEmail();
            String id = Integer.toString(products.get(chose).getId());

            NewEpisodeComics newEpisodeComics = new NewEpisodeComics(id, email);

            eventManeger.unsubscribe("new-episode", newEpisodeComics);
        } else {
            String email = ((Client)(userDatabase.getUserByLogin(this.authService.getAuthUserLogin()))).getEmail();
            NewSalesListener newSalesListener = new NewSalesListener(email);

            eventManeger.unsubscribe("new-sale", newSalesListener);
        }
    }

    private void subscribe() {
        System.out.println("enter 1 - In stock");
        System.out.println("enter 2 - New episode");
        System.out.println("enter 3 - New Sale");
        System.out.print("enter: ");
        op = sc.nextInt();

        ArrayList<Product> products = new ArrayList<>(productDatabase.getAllProducts());

        if (op == 1) {
            System.out.println("[ Products ]");
            for (int i = 0; i < products.size(); i++) {
                System.out.println("\n" + i +  ") " + products.get(i).showDetails());
            }
            System.out.print("enter: ");
            chose = sc.nextInt();

            String email = ((Client)(userDatabase.getUserByLogin(this.authService.getAuthUserLogin()))).getEmail();
            String id = Integer.toString(products.get(chose).getId());

            InStockListener inStockListener = new InStockListener(id, email);

            eventManeger.subscribe("in-stock", inStockListener);

        } else if (op == 2) {
            System.out.println("[ Products ]");
            for (int i = 0; i < products.size(); i++) {
                System.out.println("\n" + i +  ") " + products.get(i).showDetails());
            }
            System.out.print("enter: ");
            chose = sc.nextInt();

            String email = ((Client)(userDatabase.getUserByLogin(this.authService.getAuthUserLogin()))).getEmail();
            String id = Integer.toString(products.get(chose).getId());

            NewEpisodeComics newEpisodeComics = new NewEpisodeComics(id, email);

            eventManeger.subscribe("new-episode", newEpisodeComics);
        } else {
            String email = ((Client)(userDatabase.getUserByLogin(this.authService.getAuthUserLogin()))).getEmail();
            NewSalesListener newSalesListener = new NewSalesListener(email);

            eventManeger.subscribe("new-sale", newSalesListener);
        }
    }

    private void deleteNotifier() {
        System.out.println("\nenter 1 - add Email");
        System.out.println("enter 2 - add SMS");
        System.out.print("enter: ");
        op = sc.nextInt();

        if (op == 1) {
            Client client = (Client) userDatabase.getUserByLogin(authService.getAuthUserLogin());
            client.getNotifiers().remove(NotifierEnum.EMAIL);
            userDatabase.addUser(client);
        } else {
            Client client = (Client) userDatabase.getUserByLogin(authService.getAuthUserLogin());
            client.getNotifiers().remove(NotifierEnum.SMS);
            userDatabase.addUser(client);
        }

        System.out.println("\nNotifier deleted.");
    }

    private void addNotifier() {
        System.out.println("\nenter 1 - add Email");
        System.out.println("enter 2 - add SMS");
        System.out.print("enter: ");
        op = sc.nextInt();

        if (op == 1) {
            Client client = (Client) userDatabase.getUserByLogin(authService.getAuthUserLogin());
            client.getNotifiers().add(NotifierEnum.EMAIL);
            userDatabase.addUser(client);
        } else {
            Client client = (Client) userDatabase.getUserByLogin(authService.getAuthUserLogin());
            client.getNotifiers().add(NotifierEnum.SMS);
            if(client.getPhoneNumber() == null) {
                System.out.println("\nPhone Number: ");
                client.setPhoneNumber(sc.next());
            }
            userDatabase.addUser(client);
        }

        System.out.println("\nNotifier added.");
    }

    private void pay() {
        if (cart.getSize() != 0) {
            System.out.println("\nChose payment method:\nenter 1 --- Cash\nenter 2 --- Credit Card\nenter 3 -- Take installment");
            System.out.print("enter: ");
            op = sc.nextInt();

            PayStrategy payStrategy;

            if (op == 1) {
                payStrategy = new PayByCash();
            } else if (op == 2){
                payStrategy = new PayByCreditCard();
            } else {
                payStrategy = new PayByInstalments();
            }

            Client client = (Client) userDatabase.getUserByLogin(authService.getAuthUserLogin());
            int cost = cart.getTotalCost();

            payStrategy.collectPaymentDetails(cost, client);

            if (payStrategy.pay()) {
                cart.clear();
                System.out.println("\nPayment has been successful.");
            } else {
                System.out.println("\nFAIL! Please, check your data.");
            }
        } else {
            System.out.println("\nCart is empty!");
        }
    }

    private void actionRemoveItem() {
        if (cart.getSize() == 0) {
            System.out.println("\nCart is empty!");
        } else {
            cart.show();
            System.out.print("enter: ");
            chose = sc.nextInt();

            if (chose > -1 && chose < cart.getSize()) {
                cart.removeProduct(chose);
            }
        }
    }

    private void signUp() {
        String login, password, firstName, lastName;
        System.out.println("\nenter 1 - Client");
        System.out.println("enter 2 - Admin");
        System.out.print("enter: ");
        op = sc.nextInt();

        System.out.print("\nFirst Name: ");
        firstName = sc.next();
        System.out.print("Last Name: ");
        lastName = sc.next();

        if (op == 1) {
            while(true) {
                System.out.print("Email: ");
                login = sc.next();

                if (userDatabase.hasLogin(login)) {
                    System.out.println("input other email.");
                } else {
                    break;
                }
            }
            System.out.print("Password: ");
            password = sc.next();

            userDatabase.addUser(new Client(firstName, lastName, login, password));
            System.out.println("Created!");
        } else if (op == 2) {
            while(true) {
                System.out.print("Login: ");
                login = sc.next();

                if (userDatabase.hasLogin(login)) {
                    System.out.println("input other login.");
                } else {
                    break;
                }
            }

            System.out.print("Password: ");
            password = sc.next();

            userDatabase.addUser(new Admin(firstName, lastName, login, password));
        } else {
            showExitMessage();
        }


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
            if (products.get(i).getQuantity() > 0) {
                str = "in stock";
            } else {
                str = "out of stock";
            }

            System.out.println("\n" + i +  ") " + products.get(i).showDetails() + "\nStatus: " + str);
        }

        System.out.print("enter: ");
        chose = sc.nextInt();

        if (products.size() > chose) {
            if (products.get(chose).getQuantity() == 0) {
                System.out.println("\nThis product is out of Stock.");
                System.out.println("\nDo you want to subscribe to this product?");
                System.out.println("enter 1 - YES");
                System.out.println("enter 2 - NO");
                System.out.print("enter: ");
                op = sc.nextInt();

                if (op == 1) {
                    String id = Integer.toString(products.get(chose).getId());
                    Client client = (Client) userDatabase.getUserByLogin(this.authService.getAuthUserLogin());
                    InStockListener inStockListener = new InStockListener(id, client.getEmail());
                    eventManeger.subscribe("in-stock", inStockListener);
                }
            } else {
                cart.addProduct(products.get(chose));
                productDatabase.decrementQuantity(products.get(chose));
                System.out.println("\nAdded.");
            }
        } else {
            showErrorInvalidArgument();
        }
    }

    private void showErrorInvalidArgument() {
        System.out.println("\nInvalid argument!");
    }

    private void showExitMessage() {
        System.out.println("\n--- EXIT APP ---");
    }
}
