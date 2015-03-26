package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.entity.RmbsApplication;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsApplicationDao")
@Lazy
public class RmbsApplicationDaoImpl extends IDAOImpl<RmbsApplication> implements RmbsApplicationDao {

	@Override
	public Class<RmbsApplication> getEntityClass() {
		return RmbsApplication.class;
		
	}

	
}
