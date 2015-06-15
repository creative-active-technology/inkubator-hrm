/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.web.search.HolidaySearchParameter;

import java.util.Date;
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
 * @author Deni Husni FR
 */
@Repository(value = "wtHolidayDao")
@Lazy
public class WtHolidayDaoImpl extends IDAOImpl<WtHoliday> implements WtHolidayDao {

    @Override
    public Class<WtHoliday> getEntityClass() {
        return WtHoliday.class;
    }

    @Override
    public List<WtHoliday> getByParam(HolidaySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtHolidayByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFetchMode("religion", FetchMode.JOIN);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalWtHolidayByParam(HolidaySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtHolidayByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchWtHolidayByParam(HolidaySearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getReligionName() != null && !searchParameter.getReligionName().isEmpty()) {
            criteria.createAlias("religion", "r");
            criteria.add(Restrictions.like("r.name", searchParameter.getReligionName(), MatchMode.START));
        }

        if (searchParameter.getHolidayName() != null && !searchParameter.getHolidayName().isEmpty()) {
            criteria.add(Restrictions.like("holidayName", searchParameter.getHolidayName(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalWtHolidayByHolidayName(String holidayName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("holidayName", holidayName));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalWtHolidayByNameAndNotId(String holidayName, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("holidayName", holidayName));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<WtHoliday> getBetweenDate(Date start, Date end) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.between("holidayDate", start, end));
        return criteria.list();

    }
    
    @Override
	public Long getTotalBetweenDate(Date start, Date end) {
    	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.between("holidayDate", start, end));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

    @Override
    public WtHoliday getWtHolidayByDate(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("holidayDate", date));
        return (WtHoliday) criteria.uniqueResult();
    }

    @Override
    public List<WtHoliday> getByYearDif(int value) {
        Date now = new Date();
        Date parameter = DateTimeUtil.getDateFrom(now, -value, CommonUtilConstant.DATE_FORMAT_YEAR);
      
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.lt("holidayDate", parameter));
        criteria.add(Restrictions.eq("isEveryYear", 1));
        return criteria.list();
    }

    @Override
    public String getWtHolidayNameByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("holidayName", name));
        return (String) criteria.setProjection(Projections.property("holidayName")).uniqueResult();
    }

	@Override
	public List<WtHoliday> getListPublicNonReligionHolidayBetweenDate(Date start,	Date end) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("religion", FetchMode.JOIN);
        criteria.add(Restrictions.ge("holidayDate", start));
        criteria.add(Restrictions.le("holidayDate", end));
        criteria.add(Restrictions.isNull("religion"));       
        
        return criteria.list();
	}

	@Override
	public List<WtHoliday> getListPublicReligionHolidayByReligionCodeAndBetweenDate(Date start, Date end, String religionCode) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		//criteria.setFetchMode("religion", FetchMode.JOIN);
		criteria.createAlias("religion", "religion", JoinType.INNER_JOIN);
		criteria.add(Restrictions.isNotNull("religion"));    
        criteria.add(Restrictions.ge("holidayDate", start));
        criteria.add(Restrictions.le("holidayDate", end));
        criteria.add(Restrictions.eq("religion.code", religionCode));
        
        
        return criteria.list();
	}

}
