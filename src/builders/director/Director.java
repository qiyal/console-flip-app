package builders.director;


import builders.ComicsBuilder;
import products.Comics;
import products.ComicsPublisherEnum;

import java.util.HashSet;
import java.util.Set;

public class Director {
    public Comics buildFullmetalAlchemistComics(ComicsBuilder builder) {
        Set<String> set = new HashSet<>();
        set.add("Аракава Х.");
        return new Comics(
                builder
                        .setName("Fullmetal AlchemistComics")
                        .setAuthorName(set)
                        .setPrice(2000)
                        .setYear(2020)
                        .setPublisher(ComicsPublisherEnum.MANGA)
                        .setQuantity(40)
                        .setIdPart(11)
                        .setPart(100)
                        .setUrlImage("book11.jpg")
                        .getResult()
        );
    }

    public Comics buildMajorThunder(ComicsBuilder builder) {
        Set<String> set = new HashSet<>();
        set.add("Федотов Е.");
        set.add("Габрелянов А.");
        return new Comics(
                builder
                        .setName("Major Thunder")
                        .setAuthorName(set)
                        .setPrice(2000)
                        .setYear(2020)
                        .setPublisher(ComicsPublisherEnum.MANGA)
                        .setQuantity(40)
                        .setIdPart(13)
                        .setPart(12)
                        .setUrlImage("book13.jpg")
                        .getResult()
        );
    }
}
