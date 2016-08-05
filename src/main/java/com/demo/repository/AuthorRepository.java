package com.demo.repository;

import com.demo.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Holds the CRUD operations for {@link Author} entity.
 *
 * @author rburawes
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByFamilyName(String name);
}


