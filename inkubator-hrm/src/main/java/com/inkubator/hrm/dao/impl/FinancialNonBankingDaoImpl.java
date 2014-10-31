package com.inkubator.hrm.dao.impl;

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

}
