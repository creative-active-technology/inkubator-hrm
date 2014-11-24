package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FingerMatchEmpDao;
import com.inkubator.hrm.entity.FingerMatchEmp;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "fingerMatchEmpDao")
@Lazy
public class FingerMatchEmpDaoImpl extends IDAOImpl<FingerMatchEmp> implements FingerMatchEmpDao {

	@Override
	public Class<FingerMatchEmp> getEntityClass() {
		return FingerMatchEmp.class;
		
	}

	
}
