package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SavingTypeDao;
import com.inkubator.hrm.entity.SavingType;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "savingTypeDao")
@Lazy
public class SavingTypeDaoImpl extends IDAOImpl<SavingType> implements SavingTypeDao {

	@Override
	public Class<SavingType> getEntityClass() {
		return SavingType.class;
		
	}

	
}
