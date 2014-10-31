package com.inkubator.hrm.dao.impl;

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

	
}
