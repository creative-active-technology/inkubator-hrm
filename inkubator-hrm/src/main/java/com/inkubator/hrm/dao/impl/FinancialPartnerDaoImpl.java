package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FinancialPartnerDao;
import com.inkubator.hrm.entity.FinancialPartner;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "financialPartnerDao")
@Lazy
public class FinancialPartnerDaoImpl extends IDAOImpl<FinancialPartner> implements FinancialPartnerDao {

	@Override
	public Class<FinancialPartner> getEntityClass() {
		return FinancialPartner.class;
		
	}

	@Override
	public List<FinancialPartner> getAllDataByCompanyId(Long companyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("company.id", companyId));
		criteria.setFetchMode("financialNonBanking", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public FinancialPartner getEntityByPKWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("financialNonBanking", FetchMode.JOIN);
		return (FinancialPartner) criteria.uniqueResult();
	}
	
	@Override
	public Long getTotalByAccountNumber(Integer accountNumber) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("accountNumber", accountNumber));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByAccountNumberAndNotId(Integer accountNumber, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("accountNumber", accountNumber));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
}
