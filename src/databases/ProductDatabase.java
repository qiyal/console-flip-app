package databases;

import products.*;
import users.Admin;

import java.util.*;

public class ProductDatabase {
    private static ProductDatabase instance;
    private SortedMap<Integer, Product> books = new TreeMap<>();

    {
        Set<String> setNames = new HashSet<>();
        setNames.add("Douglas Noël Adams");

        Book b1 = new Book(1, "The Hitchhiker’s Guide to the Galaxy", setNames, 1260,
                BookGenreEnum.SCIENCE_FICTION, 2014, BookPublisherEnum.ACT, 10, 1, 1, "book1.jpg");

        Book b2 = new Book(2, "The Restaurant at the End of the Universe", setNames, 2100,
                BookGenreEnum.SCIENCE_FICTION, 2017, BookPublisherEnum.ACT, 5, 1, 2, "book2.jpg");

        setNames.clear();
        setNames.add("Antoine Marie Jean-Baptiste Roger de Saint-Exupéry");
        Book b3 = new Book(3, "Le Petit Prince", setNames, 990,
                BookGenreEnum.PROSE, 2017, BookPublisherEnum.EKSMO, 2, 3, 1, "book3.jpg");

        setNames.clear();
        setNames.add("Eric Berne");
        Book b4 = new Book(4, "Games People Play", setNames, 2500,
                BookGenreEnum.PSYCHOLOGY, 2017, BookPublisherEnum.EKSMO, 0, 4, 1, "book4.jpg");

        setNames.clear();
        setNames.add("Brian Tracy");
        Book b5 = new Book(5, "THE SCIENCE OF MOTIVATION", setNames, 2300,
                BookGenreEnum.PSYCHOLOGY, 2018, BookPublisherEnum.EKSMO, 0, 5, 1, "book5.jpg");

        setNames.clear();
        setNames.add("Stephen Edwin King");
        Book b6 = new Book(6, "It", setNames, 4390,
                BookGenreEnum.FANTASY, 2015, BookPublisherEnum.ACT, 100, 6, 1, "book6.jpg");

        setNames.clear();
        setNames.add("Joanne Katheline Rowling");
        Book b7 = new Book(7, "Harry Potter and the Philosopher's Stone", setNames, 4390,
                BookGenreEnum.PROSE, 2019, BookPublisherEnum.STEPPE, 20, 7, 1, "book7.jpg");

        Book b8 = new Book(8, "Harry Potter and the Chamber of Secrets", setNames, 4200,
                BookGenreEnum.PROSE, 2020, BookPublisherEnum.STEPPE, 0, 7, 2, "book8.jpg");

        books.put(b1.getBookId(), b1);
        books.put(b2.getBookId(), b2);
        books.put(b3.getBookId(), b3);
        books.put(b4.getBookId(), b4);
        books.put(b5.getBookId(), b5);
        books.put(b6.getBookId(), b6);
        books.put(b7.getBookId(), b7);
        books.put(b8.getBookId(), b8);

        setNames.clear();
        setNames.add("Мур А.");
        Comics comics1 = new Comics(10, "Мур А.: Бэтмен. Убийственная шутка", setNames, 5500, 2016, ComicsPublisherEnum.DC, 0, 10, 1, "book10.jpg");

        setNames.clear();
        setNames.add("Аракава Х.");
        Comics comics2 = new Comics(11, "Стальной Алхимик", setNames, 5750, 2019, ComicsPublisherEnum.MANGA, 1, 11, 1, "book11.jpg");

        Comics comics3 = new Comics(12, "Стальной Алхимик",setNames, 5400, 2019, ComicsPublisherEnum.MANGA, 2, 11, 2, "book12.jpg");

        setNames.clear();
        setNames.add("Федотов Е.");
        setNames.add("Габрелянов А.");
        Comics comics4 = new Comics(13, "Майор Гром", setNames, 2750, 2015, ComicsPublisherEnum.BUBBLE, 1, 13, 1, "book13.jpg");

        books.put(comics1.getComicsId(), comics1);
        books.put(comics2.getComicsId(), comics2);
        books.put(comics3.getComicsId(), comics3);
        books.put(comics4.getComicsId(), comics4);
    }

    private ProductDatabase() {}

    public static ProductDatabase getInstance() {
        if (instance == null)
            instance = new ProductDatabase();
        return instance;
    }

    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(books.values());
    }

    public int size() {
        return books.size();
    }

    public Product getProductById(int id) {
        return books.getOrDefault(id, null);
    }

    public void decrementQuantity(Product product) {
        if (product instanceof Book) {
            Book book = (Book)product;
            book.setQuantity(book.getQuantity() - 1);
            books.put(book.getBookId(), book);
        } else {
            Comics comics = ((Comics)product);
            comics.setQuantity(comics.getQuantity() - 1);
            books.put(comics.getComicsId(), comics);
        }
    }

    public void incrementQuantity(Product product) {
        if (product instanceof Book) {
            Book book = (Book)product;
            book.setQuantity(book.getQuantity() + 1);
            books.put(book.getBookId(), book);
        } else {
            Comics comics = ((Comics)product);
            comics.setQuantity(comics.getQuantity() + 1);
            books.put(comics.getComicsId(), comics);
        }
    }



}
