 
package com.spring.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.spring.dao.ConstantDAO;
import com.spring.domain.Constant;

@Repository("constantDAO")
public class ConstantDAOImpl extends BaseDAOImpl<Constant, Long> implements ConstantDAO {

	/*
	* (non-Javadoc)
	*
	* @see com.spring.dao.impl.BaseDAOImpl#getDataClass()
	*/
	@Override
	protected Class getDataClass() {
		return Constant.class;
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see com.flipswap.dao.impl.hibernate.ConstantDAO#findConstantByCode(java.lang.String,
     *      java.lang.Class)
     */
    @SuppressWarnings("unchecked")
	public <T extends Constant> T findConstantByCode(final String code,
            final Class<T> codeTypeClass) throws DataAccessException {
    	Criteria c = getSession().createCriteria(codeTypeClass);
    	c.add(Restrictions.eq("code", code));
    	/*DetachedCriteria dc = DetachedCriteria.forClass(codeTypeClass);
    	dc.add(Restrictions.eq("code", code));*/
        return (T) DataAccessUtils.uniqueResult(c.list());    	
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.flipswap.dao.impl.hibernate.ConstantDAO#findConstants(java.lang.Class)
     */
    public <T extends Constant> List<T> findConstants(Class<T> codeTypeClass)
            throws DataAccessException {
    	Criteria c = getSession().createCriteria(codeTypeClass)
                .addOrder(Order.asc("code"));
        return c.list();
    }
}