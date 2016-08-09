package com.demo.repository;

import com.demo.DataDemoApplication;
import com.demo.model.Author;
import com.demo.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test things on {@link BookRepository}.
 * Test application's custom methods define at {@link CustomRepository}.
 *
 * @author rburawes
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataDemoApplication.class)
@Transactional
public class BookRepositoryPostgresTest {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void addBook() throws Throwable {

        List<Author> authors = new ArrayList<>();

        Book book = new Book();
        book.setTitle("Python Cookbook");

        Author author = new Author();
        author.setGivenName("David");
        author.setFamilyName("Ascher");
        authors.add(author);

        author = new Author();
        author.setGivenName("Alex");
        author.setFamilyName("Martelli");
        authors.add(author);

        author = new Author();
        author.setGivenName("Anna");
        author.setFamilyName("Martelli Ravenscropt");
        authors.add(author);

        book.setAuthors(authors);

        bookRepository.save(book);
    }

    /**
     * Test the 'get_book_details' function from PostgreSQL database.
     * @throws Throwable
     */
    @Test
    public void testStoredProcedureGetBookDetails() throws Throwable {

        long id = 1l;
        List<Book> books = bookRepository.executeStoredProcedure("Book.details", Arrays.asList(new Object[]{id}));
        Assert.assertTrue(books != null);
    }
}