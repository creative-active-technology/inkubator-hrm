package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApplicantDao;
import com.inkubator.hrm.entity.Applicant;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "applicantDao")
@Lazy
public class ApplicantDaoImpl extends IDAOImpl<Applicant>implements ApplicantDao {

	@Override
	public Class<Applicant> getEntityClass() {
		
		return Applicant.class;
	}

}
