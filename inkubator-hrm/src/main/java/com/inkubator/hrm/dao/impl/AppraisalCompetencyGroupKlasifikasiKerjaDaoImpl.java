package com.inkubator.hrm.dao.impl;

import org.hibernate.Query;
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

	@Override
	public void deleteByCompetencyGroupId(Long competencyGroupId) {
		Query query = getCurrentSession().createQuery("DELETE FROM AppraisalCompetencyGroupKlasifikasiKerja temp WHERE temp.appraisalCompetencyGroup.id = :competencyGroupId")
				.setLong("competencyGroupId", competencyGroupId);
        query.executeUpdate();		
	}

}
