package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHitungLemburDao;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHitungLembur;

@Repository(value = "wtHitungLemburDao")
@Lazy
public class WtHitungLemburDaoImpl extends IDAOImpl<WtHitungLembur> implements WtHitungLemburDao {

	@Override
	public Class<WtHitungLembur> getEntityClass() {
		return WtHitungLembur.class;
	}
	
}
