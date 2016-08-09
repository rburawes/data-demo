package com.demo.repository;

import com.demo.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import java.util.List;

/**
 * Holds the CRUD operations for {@link Book} entity.
 *
 * @author rburawes
 */
public interface BookRepository extends CustomRepository<Book, Long> {

    @EntityGraph(value = "Book.summary", type = EntityGraphType.LOAD)
    List<Book> findAllDistinctBy();

    @EntityGraph(attributePaths = {"title"})
    Book getBookByTitle(String title);

}
