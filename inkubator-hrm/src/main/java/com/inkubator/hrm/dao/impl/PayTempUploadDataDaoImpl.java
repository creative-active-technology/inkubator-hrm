package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempUploadDataDao;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;

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
	public List<PayTempUploadData> getAllDataByParam(PayTempUploadDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(PayTempUploadDataSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(PayTempUploadDataSearchParameter parameter, Criteria criteria){
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
		
		if (parameter.getNik() != null) {
            criteria.add(Restrictions.like("empData.nik", parameter.getNik(), MatchMode.ANYWHERE));
        }

        if (parameter.getName() != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        
        if(parameter.getPaySalaryComponentId() != null){
        	criteria.add(Restrictions.eq("paySalaryComponent.id", parameter.getPaySalaryComponentId()));
        }
        criteria.add(Restrictions.isNotNull("id"));
	}
	
	@Override
	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("paySalaryComponent.id", paySalaryComponentId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	public Double getTotalSalaryByPaySalaryComponentId(Long paySalaryComponentId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("paySalaryComponent.id", paySalaryComponentId));
        return (Double) criteria.setProjection(Projections.sum("nominalValue")).uniqueResult();
	}
	
	@Override
    public PayTempUploadData getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        return (PayTempUploadData) criteria.uniqueResult();
    }

	@Override
	public void deleteByPaySalaryComponentId(Long paySalaryComponentId) {
		Query query = getCurrentSession().createQuery("delete from PayTempUploadData temp where temp.paySalaryComponent.id = :paySalaryComponentId").setLong("paySalaryComponentId", paySalaryComponentId);
		query.executeUpdate();
	}

}
