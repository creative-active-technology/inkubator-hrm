package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempProcessReadFingerDao;
import com.inkubator.hrm.entity.TempProcessReadFinger;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "tempProcessReadFingerDao")
@Lazy
public class TempProcessReadFingerDaoImpl extends IDAOImpl<TempProcessReadFinger> implements TempProcessReadFingerDao {

	@Override
	public Class<TempProcessReadFinger> getEntityClass() {
		return TempProcessReadFinger.class;
	}

	@Override
	public List<TempProcessReadFinger> getByParam(Long empDataId, Date startDate, Date endDate, int firstResult, int maxResults, Order orderable) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(Long empDataId, Date startDate, Date endDate) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	

}
