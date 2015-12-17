package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalCompetencyGroupKlasifikasiKerja;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalCompetencyGroupKlasifikasiKerjaDao extends IDAO<AppraisalCompetencyGroupKlasifikasiKerja> {

	public void deleteByCompetencyGroupId(Long competencyGroupId);
	
}
