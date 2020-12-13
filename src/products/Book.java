package products;

import java.util.Set;

public class Book implements Product {
    private int bookId;
    private String name;
    private Set<String> authorName;
    private int price;
    private BookGenreEnum genre;
    private int year;
    private BookPublisherEnum publisher;
    private int quantity;
    private int idPart;
    private int part;
    private String urlImage;

    public Book() {}

    public Book(int bookId, String name, Set<String> authorName, int price, BookGenreEnum genre, int year, BookPublisherEnum publisher, int quantity, int idPart, int part, String urlImage) {
        this.bookId = bookId;
        this.name = name;
        this.authorName = authorName;
        this.price = price;
        this.genre = genre;
        this.year = year;
        this.publisher = publisher;
        this.quantity = quantity;
        this.idPart = idPart;
        this.part = part;
        this.urlImage = urlImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public int getBookId() {
        return bookId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String showDetails() {
        return name + "\n" +
                "authors: " + authorName.toString() + "\n" +
                "Year: " + year + "\n" +
                "Genre: " + genre.toString().toLowerCase() + "\n" +
                "publisher: " + publisher.toString().toLowerCase() + "\n" +
                "Price: " + price;
    }

    public Set<String> getAuthorName() {
        return authorName;
    }

    @Override
    public int getId() {
        return this.getBookId();
    }

    @Override
    public int getPrice() {
        return price;
    }

    public BookGenreEnum getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public BookPublisherEnum getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getIdPart() {
        return idPart;
    }

    public int getPart() {
        return part;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public void setPart(int part) {
        this.part = part;
    }
}
