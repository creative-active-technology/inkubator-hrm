package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyTypeGolJabDao;
import com.inkubator.hrm.entity.AppraisalCompetencyTypeGolJab;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Repository(value = "appraisalCompetencyTypeGolJabDao")
@Lazy
public class AppraisalCompetencyTypeGolJabDaoImpl extends IDAOImpl<AppraisalCompetencyTypeGolJab> implements AppraisalCompetencyTypeGolJabDao {

	@Override
	public Class<AppraisalCompetencyTypeGolJab> getEntityClass() {
		return AppraisalCompetencyTypeGolJab.class;
	}

}
