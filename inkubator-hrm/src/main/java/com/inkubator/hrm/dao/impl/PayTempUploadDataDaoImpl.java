package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempUploadDataDao;
import com.inkubator.hrm.entity.PayTempUploadData;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "payTempUploadDataDao")
@Lazy
public class PayTempUploadDataDaoImpl extends IDAOImpl<PayTempUploadData> implements PayTempUploadDataDao {

	@Override
	public Class<PayTempUploadData> getEntityClass() {
		return PayTempUploadData.class;		
	}
	
	@Override
	public List<PayTempUploadData> getAllDataByPaySalaryComponentId(Long paySalaryComponentId, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("paySalaryComponent.id", paySalaryComponentId));
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("paySalaryComponent.id", paySalaryComponentId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
    public PayTempUploadData getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        return (PayTempUploadData) criteria.uniqueResult();
    }

}
