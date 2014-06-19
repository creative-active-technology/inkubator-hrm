/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

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
//        System.out.println(" Ni lai 1 " + searchParameter.getHolidayName());
//        System.out.println(" Ni lai 1 " + searchParameter.getReligionName());
        if (searchParameter.getReligionName() != null && !searchParameter.getReligionName().isEmpty()) {
            criteria.createAlias("religion", "r");
            criteria.add(Restrictions.like("r.name", searchParameter.getReligionName(), MatchMode.ANYWHERE));
        }

        if (searchParameter.getHolidayName() != null && !searchParameter.getHolidayName().isEmpty()) {
            criteria.add(Restrictions.like("holidayName", searchParameter.getHolidayName(), MatchMode.ANYWHERE));
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

}
