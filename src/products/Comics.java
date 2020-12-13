package products;

import java.util.Set;

public class Comics implements Product {
    public int comicsId;
    public String name;
    private Set<String> authorName;
    private int price;
    private int year;
    private ComicsPublisherEnum publisher;
    private int quantity;
    private int idPart;
    private int part;
    private String urlImage;


    public Comics(int comicsId, String name, Set<String> authorName, int price, int year, ComicsPublisherEnum publisher, int quantity, int idPart, int part, String urlImage) {
        this.comicsId = comicsId;
        this.name = name;
        this.authorName = authorName;
        this.price = price;
        this.year = year;
        this.publisher = publisher;
        this.quantity = quantity;
        this.idPart = idPart;
        this.part = part;
        this.urlImage = urlImage;
    }



    public int getComicsId() {
        return comicsId;
    }

    public void setComicsId(int comicsId) {
        this.comicsId = comicsId;
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
                "publisher: " + publisher.toString().toLowerCase() + "\n" +
                "Price: " + price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(Set<String> authorName) {
        this.authorName = authorName;
    }

    @Override
    public int getId() {
        return this.getComicsId();
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ComicsPublisherEnum getPublisher() {
        return publisher;
    }

    public void setPublisher(ComicsPublisherEnum publisher) {
        this.publisher = publisher;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
