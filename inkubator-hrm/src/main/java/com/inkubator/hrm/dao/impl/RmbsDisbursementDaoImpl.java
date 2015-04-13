package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsDisbursementDao;
import com.inkubator.hrm.entity.RmbsDisbursement;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsDisbursementDao")
@Lazy
public class RmbsDisbursementDaoImpl extends IDAOImpl<RmbsDisbursement> implements RmbsDisbursementDao {

	@Override
	public Class<RmbsDisbursement> getEntityClass() {
		// TODO Auto-generated method stub
		return RmbsDisbursement.class;
	}

	@Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
    }	

}
