package com.demo.repository;

import com.demo.model.Author;

import java.util.List;

/**
 * Holds the CRUD operations for {@link Author} entity.
 *
 * @author rburawes
 */
public interface AuthorRepository extends CustomRepository<Author, Long> {

    List<Author> findByFamilyName(String name);
}