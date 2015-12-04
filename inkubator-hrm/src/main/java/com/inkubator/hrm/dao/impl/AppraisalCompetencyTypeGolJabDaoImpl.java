package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public List<AppraisalCompetencyTypeGolJab> getListByAppraisalCompetenceTypeId(Long appraisalCompetenceTypeId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("appraisalCompetencyType", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.add(Restrictions.eq("appraisalCompetencyType.id", appraisalCompetenceTypeId));
        return criteria.list();
	}

}
