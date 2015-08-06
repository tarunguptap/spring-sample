package com.spring.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.dao.DAO;

@Repository
public class BaseDAOImpl<T, Id extends Serializable> implements DAO<T, Id> {

	private Class<T> clazz;
	
	protected Class<T> getDataClass() {
		return clazz;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public BaseDAOImpl() {
	}

	/**
	 * 
	 * @param cls
	 */
	public BaseDAOImpl(final Class<T> cls) {
		clazz = cls;
	}

	@Override
	public void delete(final Id id) throws DataAccessException {
		getSession().delete(findByPK(id));
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findByPK(final Id id) throws DataAccessException {
		return (T) getSession().get(getDataClass(), id);
	}

	

	/**
	 * @param dto
	 * @return
	 */
	@Override
	public void saveOrUpdate(final T entity) throws DataAccessException {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void saveUpdateAndFlush(final T entity) throws DataAccessException {
		getSession().saveOrUpdate(entity);
		getSession().flush();
	}

	@Override
	public void evict(final T domain) throws DataAccessException {
		getSession().evict(domain);
	}

	@Override
	public void evict(final Collection<T> domainSet) {
		for (final T domain : domainSet) {
			getSession().evict(domain);
		}
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public void update(final T domain) {
		getSession().update(domain);
	}

	@Override
	public void update(final Collection<T> domainSet) {
		for (final T domain : domainSet) {
			getSession().update(domain);
		}
	}

	@Override
	public void refresh(final Collection<T> domainSet) {
		for (final T domain : domainSet) {
			getSession().refresh(domain);
		}
	}

	@Override
	public void refresh(final T domain) {
		getSession().refresh(domain);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T merge(final T entity) throws DataAccessException {
		return (T) getSession().merge(entity);
	}

}