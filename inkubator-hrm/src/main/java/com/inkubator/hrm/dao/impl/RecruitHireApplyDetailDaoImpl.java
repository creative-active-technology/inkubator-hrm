/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitHireApplyDetailDao;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitHireApplyDetailDao")
@Lazy
public class RecruitHireApplyDetailDaoImpl extends IDAOImpl<RecruitHireApplyDetail> implements RecruitHireApplyDetailDao {

    @Override
    public Class<RecruitHireApplyDetail> getEntityClass() {
        return RecruitHireApplyDetail.class;
    }

    @Override
    public List<RecruitHireApplyDetail> getListWithDetailByRecruitHireApplyId(Long recruitHireApplyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("orgTypeOfSpecList", FetchMode.JOIN);
        criteria.setFetchMode("recruitHireApply", FetchMode.JOIN);
        criteria.add(Restrictions.eq("recruitHireApply.id", recruitHireApplyId));       
        return criteria.list();
    }
    
}
