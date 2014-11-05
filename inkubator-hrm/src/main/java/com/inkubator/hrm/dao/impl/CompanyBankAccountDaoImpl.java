package com.inkubator.hrm.dao.impl;

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

}
