package com.demo.search.repository;

import com.demo.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test things on {@link BookSearchRepository}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookSearchRepositoryTest {

    @Autowired
    private BookSearchRepository bookRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Before

    public void before() {

        elasticsearchTemplate.deleteIndex(Book.class);
        elasticsearchTemplate.createIndex(Book.class);
        elasticsearchTemplate.putMapping(Book.class);
        elasticsearchTemplate.refresh(Book.class);
    }

    @Test
    public void shouldCreateSingleBookEntityWitRepository(){

        //given
        Book book = new Book();
        book.setBookId("1");
        book.setTitle("Learning Scala");

        //when
        Book indexedBook = bookRepository.save(book);

        //then
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9200/store/book/1", String.class);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        assertThat(indexedBook, is(notNullValue()));
        assertThat(indexedBook.getBookId(), is(book.getBookId()));
        assertThat(bookRepository.findOne("1"), is(notNullValue()));
    }
}