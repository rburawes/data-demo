package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the details of book.
 */
@Entity
@Table(name = "book")
@NamedEntityGraphs(
    @NamedEntityGraph(name = "Book.summary", attributeNodes = { @NamedAttributeNode("id"), @NamedAttributeNode("title") }))
@JsonIgnoreProperties(value = { "authors" })
@Document(indexName = "store", type = "book")
public class Book extends Modifiable {

    @Transient
    @org.springframework.data.annotation.Id
    private String bookId;

    @Column(name = "title")
    @Field(type = FieldType.String, store = true)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    @Field(type = FieldType.String, store = true)
    private List<Author> authors = new ArrayList<>();

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
