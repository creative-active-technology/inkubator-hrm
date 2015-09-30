package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApplicantSpecListDao;
import com.inkubator.hrm.entity.ApplicantSpecList;

/**
 *
 * @author rizkykojek
 */
public class ApplicantSpecListDaoImpl extends IDAOImpl<ApplicantSpecList>implements ApplicantSpecListDao {

	@Override
	public Class<ApplicantSpecList> getEntityClass() {
		// TODO Auto-generated method stub
		return ApplicantSpecList.class;
	}

	

}
