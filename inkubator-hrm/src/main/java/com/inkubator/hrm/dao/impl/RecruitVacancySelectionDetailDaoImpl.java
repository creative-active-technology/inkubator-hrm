/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitVacancySelectionDetailDao;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetail;

/**
 *
 * @author Deni
 */
@Repository(value = "recruitVacancySelectionDetailDao")
@Lazy
public class RecruitVacancySelectionDetailDaoImpl extends IDAOImpl<RecruitVacancySelectionDetail> implements RecruitVacancySelectionDetailDao {

    @Override
    public Class<RecruitVacancySelectionDetail> getEntityClass() {
        return RecruitVacancySelectionDetail.class;
    }

	@Override
	public List<RecruitVacancySelectionDetail> getAllDataByRecruitVacancySelection(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
	    criteria.add(Restrictions.eq("recruitVacancySelection.id", id));
		criteria.setFetchMode("recruitSelectionType", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByRecruitVacancySelection(
			Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
	    criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("recruitSelectionType", FetchMode.JOIN);
		return (RecruitVacancySelectionDetail) criteria.uniqueResult();
	}
    
}
