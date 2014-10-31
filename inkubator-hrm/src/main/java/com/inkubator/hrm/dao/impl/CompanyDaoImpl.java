package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.entity.Company;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "companyDao")
@Lazy
public class CompanyDaoImpl extends IDAOImpl<Company> implements CompanyDao {

	@Override
	public Class<Company> getEntityClass() {
		return Company.class;		
	}

}
