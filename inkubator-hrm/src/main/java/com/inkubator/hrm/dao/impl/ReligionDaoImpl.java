package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ReligionDao;
import com.inkubator.hrm.entity.Religion;

/**
*
* @author rizkykojek
*/
@Repository
@Lazy
public class ReligionDaoImpl extends IDAOImpl<Religion> implements ReligionDao {

	@Override
	public Class<Religion> getEntityClass() {
		return Religion.class;
	}

}
