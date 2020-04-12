package tech.donau.data;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

public class Book {
    private final String id;
    private final String title;
    private final String author;

    @ProtoFactory
    public Book(String id, String title, String author) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
    }

    @ProtoField(number = 1)
    public String getId() {
        return id;
    }

    @ProtoField(number = 2)
    public String getTitle() {
        return title;
    }

    @ProtoField(number = 3)
    public String getAuthor() {
        return author;
    }
}
