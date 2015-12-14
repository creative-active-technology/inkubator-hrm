/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitLettersDao;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.search.RecrutimentLetterSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
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

    @Override
    public List<RecruitLetters> getAllWithSpecificLetterType(int type) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("leterTypeId", type));
        return criteria.list();
    }

    @Override
    public void saveAndMerge(RecruitLetters letters) {
        getCurrentSession().update(letters);
        getCurrentSession().flush();
    }

    @Override
    public List<RecruitLetters> getByParam(RecrutimentLetterSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitLettersByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(RecrutimentLetterSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitLettersByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchRecruitLettersByParam(RecrutimentLetterSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getSelectionType())) {
            criteria.createAlias("recruitLetterSelections", "rs", JoinType.INNER_JOIN);
            criteria.createAlias("rs.recruitSelectionType", "se", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("se.name", parameter.getSelectionType(), MatchMode.ANYWHERE));

        }
        if (StringUtils.isNotEmpty(parameter.getLetterType())) {
            criteria.add(Restrictions.eq("leterTypeId", Integer.parseInt(parameter.getLetterType())));
        }
        if (StringUtils.isNotEmpty(parameter.getSenderBy())) {
            criteria.createAlias("recruitLetterComChannels", "rc", JoinType.INNER_JOIN);
            criteria.createAlias("rc.recruitCommChannels", "rcom", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("rcom.channelName", parameter.getSenderBy(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public RecruitLetters sanvAndFlus(RecruitLetters letters) {
        getCurrentSession().save(letters);
        getCurrentSession().flush();
        return letters;
    }

	@Override
	public RecruitLetters getEntityMostUpdatedByLetterTypeId(Integer letterTypeId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("leterTypeId", letterTypeId));
        criteria.addOrder(Order.desc("createdOn"));
        criteria.setMaxResults(1);
        
        RecruitLetters entity = null;
        List<RecruitLetters> list = criteria.list();
        if(!list.isEmpty()){
        	entity = list.get(0);
        }
        return entity;
	}

}
