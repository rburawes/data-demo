package com.demo.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Implements the custom methods from {@link CustomRepository}.
 * @param <T>
 * @param <ID>
 *
 *  @author rburawes
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    private EntityManager em;

    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public List<T> executeStoredProcedure(String storedProcedureName, List<Object> params){

        StoredProcedureQuery query = em.createNamedStoredProcedureQuery(storedProcedureName);
        // The REF_CURSOR must be the first parameter to prevent 'postgreSQL supports only one REF_CURSOR parameter, but multiple were registered' error.
        // Note, List's index starts with '0'.
        params.stream().forEach(param -> query.setParameter(params.indexOf(param)+2,param));

        return query.getResultList();
    }
}