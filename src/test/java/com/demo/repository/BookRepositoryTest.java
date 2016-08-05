package com.demo.repository;

import com.demo.model.Author;
import com.demo.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Test things on {@link BookRepository}.
 *
 * @author rburawes
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class BookRepositoryTest {


    @Autowired
    private BookRepository bookRepository;

    @Before
    public void addBook() throws Throwable {

        List<Author> authors = new ArrayList<>();

        Book book = new Book();
        book.setTitle("Programming in Scala");

        Author author = new Author();
        author.setGivenName("Martin");
        author.setFamilyName("Odersky");
        authors.add(author);

        author = new Author();
        author.setGivenName("Lex");
        author.setFamilyName("Spoon");
        authors.add(author);

        book.setAuthors(authors);

        bookRepository.save(book);
    }

    @Test
    public void testGetBook() throws Throwable {

        Book book = bookRepository.getBookByTitle("Programming in Scala");
        Assert.assertTrue(book != null);

    }

    @Test
    public void testGetBooks() throws Throwable {

        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        Assert.assertTrue(books != null);
        assertThat(books.size(), is(1));

    }

    @Test
    public void testModifyBooks() throws Throwable {

        Book book = bookRepository.getBookByTitle("Programming in Scala");
        book.setTitle("Programming in Scala 2nd Edition");
        Book modifiedBook = bookRepository.save(book);

        Assert.assertTrue(modifiedBook != null);
        Assert.assertTrue(book.getTitle().equals("Programming in Scala 2nd Edition"));

    }

    @Test
    public void testRemoveBook() throws Throwable {

        Book book = bookRepository.getBookByTitle("Programming in Scala");
        bookRepository.delete(book);

        Assert.assertTrue(bookRepository.findAll().iterator().hasNext() == false);

    }
}