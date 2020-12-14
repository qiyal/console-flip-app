package builders;

import products.ComicsPublisherEnum;

import java.util.Set;

public class ComicsBuilder {
    public int comicsId;
    public String name;
    public Set<String> authorName;
    public int price;
    public int year;
    public ComicsPublisherEnum publisher;
    public int quantity;
    public int idPart;
    public int part;
    public String urlImage;

    public ComicsBuilder() { }

    public ComicsBuilder setComicsId(int comicsId) {
        this.comicsId = comicsId;
        return this;
    }

    public ComicsBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ComicsBuilder setAuthorName(Set<String> authorName) {
        this.authorName = authorName;
        return this;
    }

    public ComicsBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public ComicsBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public ComicsBuilder setPublisher(ComicsPublisherEnum publisher) {
        this.publisher = publisher;
        return this;
    }

    public ComicsBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ComicsBuilder setIdPart(int idPart) {
        this.idPart = idPart;
        return this;
    }

    public ComicsBuilder setPart(int part) {
        this.part = part;
        return this;
    }

    public ComicsBuilder setUrlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public ComicsBuilder getResult() {
        return this;
    }
}
