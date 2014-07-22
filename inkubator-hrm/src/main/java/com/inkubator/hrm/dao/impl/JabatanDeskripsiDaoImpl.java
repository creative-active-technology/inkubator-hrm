/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanDeskripsiDao;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.web.search.JabatanDeskripsiSearcParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@Repository(value = "jabatanDeskripsiDao")
@Lazy
public class JabatanDeskripsiDaoImpl extends IDAOImpl<JabatanDeskripsi> implements JabatanDeskripsiDao {

    @Override
    public Class<JabatanDeskripsi> getEntityClass() {
        return JabatanDeskripsi.class;
    }

    @Override
    public List<JabatanDeskripsi> getByParam(JabatanDeskripsiSearcParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalJabatanByParam(JabatanDeskripsiSearcParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(JabatanDeskripsiSearcParameter parameter, Criteria criteria) {
   
        criteria.createAlias("jabatan", "jb");
        criteria.createAlias("jb.jabatan", "jbb");
        if (StringUtils.isNotEmpty(parameter.getJobsName())) {
            criteria.add(Restrictions.like("jb.name", parameter.getJobsName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getParentJobsName())) {
            criteria.add(Restrictions.like("jbb.name", parameter.getParentJobsName(), MatchMode.ANYWHERE));
        }

    }
}
