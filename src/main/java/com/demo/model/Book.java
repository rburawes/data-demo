package com.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the details of book.
 *
 * @author rburawes
 */
@Entity
@Table(name = "book", schema = "store")
@NamedEntityGraphs(
    @NamedEntityGraph(name = "Book.summary", attributeNodes = { @NamedAttributeNode("id"), @NamedAttributeNode("title") }))

@NamedStoredProcedureQuery(name = "Book.details",
    procedureName = "store.get_book_details",
    resultClasses = Book.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)})
@Document(indexName = "store", type = "book")
public class Book extends Modifiable {

    @Column(name = "title")
    @Field(type = FieldType.String, store = true)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<Author> authors = new ArrayList<>();

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
