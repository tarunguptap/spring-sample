package com.spring.dao;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.dao.DataAccessException;

public interface DAO<T, Id extends Serializable> {

	T findByPK(Id id) throws DataAccessException;

	void saveOrUpdate(T domain) throws DataAccessException;

	void delete(final Id id) throws DataAccessException;

	T merge(T entity) throws DataAccessException;

	void evict(T domain) throws DataAccessException;

	void evict(Collection<T> domainSet);

	void clear();

	void update(T domain);

	void update(Collection<T> domainSet);

	void refresh(T domain);

	void refresh(Collection<T> domainSet);

	void saveUpdateAndFlush(T entity) throws DataAccessException;

}