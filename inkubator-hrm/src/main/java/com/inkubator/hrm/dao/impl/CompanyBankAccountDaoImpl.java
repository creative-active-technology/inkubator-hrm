package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CompanyBankAccountDao;
import com.inkubator.hrm.entity.CompanyBankAccount;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "companyBankAccountDao")
@Lazy
public class CompanyBankAccountDaoImpl extends IDAOImpl<CompanyBankAccount> implements CompanyBankAccountDao {

	@Override
	public Class<CompanyBankAccount> getEntityClass() {
		return CompanyBankAccount.class;		
	}

	@Override
	public List<CompanyBankAccount> getAllDataByCompanyId(Long companyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("company.id", companyId));
		criteria.setFetchMode("bank", FetchMode.JOIN);
		criteria.setFetchMode("savingType", FetchMode.JOIN);
		return criteria.list();		
	}

	@Override
	public CompanyBankAccount getEntityByPKWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("bank", FetchMode.JOIN);
		criteria.setFetchMode("savingType", FetchMode.JOIN);
		return (CompanyBankAccount) criteria.uniqueResult();
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
