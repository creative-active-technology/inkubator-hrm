/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitAgreementNoticeDao;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.RecruitAgreementNotice;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "recruitAgreementNoticeDaoImpl")
@Lazy
public class RecruitAgreementNoticeDaoImpl extends IDAOImpl<RecruitAgreementNotice> implements RecruitAgreementNoticeDao{

    @Override
    public Class<RecruitAgreementNotice> getEntityClass() {
        return RecruitAgreementNotice.class;
    }

	@Override
	public RecruitAgreementNotice getEntityByBioDataId(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", id));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        return (RecruitAgreementNotice) criteria.uniqueResult();
	}
    
}
