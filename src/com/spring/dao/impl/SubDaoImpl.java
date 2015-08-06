package com.spring.dao.impl;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.spring.dao.SubDao;
import com.spring.domain.ModelSub;
@Repository("subdao")
public class SubDaoImpl extends BaseDAOImpl<ModelSub, Long> implements SubDao
{
	
	/*
	* (non-Javadoc)
	*
	* @see com.spring.dao.impl.BaseDAOImpl#getDataClass()
	*/
	@Override
	protected Class getDataClass() {
		return ModelSub.class;
	}
	
	public boolean Submission(ModelSub modsub) 
	{
		try
		{
			String id;
			String name;
			id=modsub.getUid();
			name=modsub.getUname();
			System.out.println("uid is==="+id+"uname  is =="+name);
			getSession().saveOrUpdate(modsub);
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Created this method for testNg purpose
	@Override
	public ModelSub findRecordByPk(String userid) {
		Query qry = getSession().createQuery("from ModelSub where uid = :userid");
		qry.setParameter("userid", userid);
		List<ModelSub> modsub = qry.list();
		if(CollectionUtils.isNotEmpty(modsub)) {
			return modsub.get(0);
		}
		return null;
	}
	
	// Created this method for testNg purpose
	@Override
	public void saveUserRecordMultiArgument(ModelSub modsub, boolean flag) {
		getSession().saveOrUpdate(modsub);
		
	}

	@Override
	public List<ModelSub> findRecords() {
		Query qry = getSession().createQuery("from ModelSub");
		return qry.list();
	}

	@Override
	public int getTotalCount() {
		return findRecords().size();
		
	}


	
}
