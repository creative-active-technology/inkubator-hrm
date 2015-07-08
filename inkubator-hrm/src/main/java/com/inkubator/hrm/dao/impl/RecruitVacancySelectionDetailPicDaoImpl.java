/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitVacancySelectionDetailPicDao;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetailPic;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "recruitVacancySelectionDetailPicDao")
@Lazy
public class RecruitVacancySelectionDetailPicDaoImpl extends IDAOImpl<RecruitVacancySelectionDetailPic> implements RecruitVacancySelectionDetailPicDao {

    @Override
    public Class<RecruitVacancySelectionDetailPic> getEntityClass() {
        return RecruitVacancySelectionDetailPic.class;
    }

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllDataByRecruitVacancySelectionDetailId(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		System.out.println(id + " idnya");
		criteria.createAlias("recruitVacancySelectionDetail", "recruitVacancySelectionDetail", JoinType.INNER_JOIN);
		criteria.createAlias("recruitVacancySelectionDetail.recruitVacancySelection", "recruitVacancySelection", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("recruitVacancySelectionDetail.id", id));
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
		criteria.setFetchMode("empData.jabatanByJabatanId.department", FetchMode.JOIN);
		criteria.setFetchMode("empData.employeeType", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public void deleteAllDataByVacancySelectionDetailId(Long id) {
		String hqlDeletedSelectionDetailPic = "delete from RecruitVacancySelectionDetailPic rvsdp where rvsdp.recruitVacancySelectionDetail.id = :id";
        int deletedSelectionDetailPic = getCurrentSession().createQuery( hqlDeletedSelectionDetailPic ).setString( "id", String.valueOf(id) ).executeUpdate();
	
	}
    
}
