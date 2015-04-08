package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsCancelationDao;
import com.inkubator.hrm.entity.RmbsCancelation;

/**
*
* @author rizkykojek
*/
@Repository(value = "rmbsCancelationDao")
@Lazy
public class RmbsCancelationDaoImpl extends IDAOImpl<RmbsCancelation> implements RmbsCancelationDao {

	@Override
	public Class<RmbsCancelation> getEntityClass() {
		
		return RmbsCancelation.class;
	}

	@Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
    }

}
