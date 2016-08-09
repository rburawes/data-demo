package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the application's custom {@link org.springframework.data.repository.Repository} methods.
 *
 * @author rburawes
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     *
     * @param storedProcedureName
     * @param params Collection of required parameters to executed stored procedure.
     * @return
     */
    List<T> executeStoredProcedure(String storedProcedureName, List<Object> params);
}
