package com.demo.search.repository;

import com.demo.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Holds the Elasticsearch CRUD operations for {@link Book} entity.
 */
public interface BookSearchRepository extends ElasticsearchRepository<Book, Long> {
}
