package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyGroupKlasifikasiKerjaDao;
import com.inkubator.hrm.entity.AppraisalCompetencyGroupKlasifikasiKerja;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalCompetencyGroupKlasifikasiKerjaDao")
@Lazy
public class AppraisalCompetencyGroupKlasifikasiKerjaDaoImpl extends IDAOImpl<AppraisalCompetencyGroupKlasifikasiKerja>
		implements AppraisalCompetencyGroupKlasifikasiKerjaDao {

	@Override
	public Class<AppraisalCompetencyGroupKlasifikasiKerja> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalCompetencyGroupKlasifikasiKerja.class;
	}

}
