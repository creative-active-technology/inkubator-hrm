package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FingerSwapCapturedDao;
import com.inkubator.hrm.entity.FingerSwapCaptured;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "fingerSwapCapturedDao")
@Lazy
public class FingerSwapCapturedDaoImpl extends IDAOImpl<FingerSwapCaptured> implements FingerSwapCapturedDao {

	@Override
	public Class<FingerSwapCaptured> getEntityClass() {
		return FingerSwapCaptured.class;
		
	}

	@Override
	public List<FingerSwapCaptured> getAllDataByFingerIndexIdAndSwapDatetimeLogBetween(List<String> fingerIndexIds, Date startDate, Date endDate, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.in("fingerIndexId", fingerIndexIds));
		criteria.add(Restrictions.ge("swapDatetimeLog", startDate));
		criteria.add(Restrictions.le("swapDatetimeLog", endDate));
		criteria.addOrder(order);
		return criteria.list();
	}

	
}
