/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitLettersDao;
import com.inkubator.hrm.entity.RecruitLetters;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "recruitLettersDao")
@Lazy
public class RecruitLettersDaoImpl extends IDAOImpl<RecruitLetters> implements RecruitLettersDao {

    @Override
    public Class<RecruitLetters> getEntityClass() {
        return RecruitLetters.class;
    }

    @Override
    public RecruitLetters getByPkWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("recruitLetterSelections", FetchMode.JOIN);
        criteria.setFetchMode("recruitLetterSelections.recruitSelectionType", FetchMode.JOIN);
        criteria.setFetchMode("recruitLetterComChannels", FetchMode.JOIN);
        criteria.setFetchMode("recruitLetterComChannels.recruitCommChannels", FetchMode.JOIN);
        return (RecruitLetters) criteria.uniqueResult();
    }

}
