package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsApplicationDisbursementDao;
import com.inkubator.hrm.entity.RmbsApplicationDisbursement;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsApplicationDisbursementDao")
@Lazy
public class RmbsApplicationDisbursementDaoImpl extends IDAOImpl<RmbsApplicationDisbursement> implements RmbsApplicationDisbursementDao {

	@Override
	public Class<RmbsApplicationDisbursement> getEntityClass() {
		// TODO Auto-generated method stub
		return RmbsApplicationDisbursement.class;
	}

	

}
