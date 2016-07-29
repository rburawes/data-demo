package com.demo.repository;

import com.demo.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Holds the CRUD operations for {@link Book} entity.
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    @EntityGraph(value = "Book.summary", type = EntityGraphType.LOAD)
    List<Book> findAllDistinctBy();

    @EntityGraph(attributePaths = {"title"})
    Book getBookByTitle(String title);

}
