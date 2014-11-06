package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FinancialNonBankingDao;
import com.inkubator.hrm.entity.FinancialNonBanking;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "financialNonBankingDao")
@Lazy
public class FinancialNonBankingDaoImpl extends IDAOImpl<FinancialNonBanking> implements FinancialNonBankingDao {

	@Override
	public Class<FinancialNonBanking> getEntityClass() {
		return FinancialNonBanking.class;
		
	}

	@Override
	public List<FinancialNonBanking> getAllDataByFinancialService(String financialService) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("financialService", financialService));
		return criteria.list();		
	}

}
