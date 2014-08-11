/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
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
@Repository(value = "empDataDao")
@Lazy
public class EmpDataDaoImpl extends IDAOImpl<EmpData> implements EmpDataDao {

    @Override
    public Class<EmpData> getEntityClass() {
        return EmpData.class;
    }

    @Override
    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchEmpDataByParam(EmpDataSearchParameter dataSearchParameter, Criteria criteria) {
        if (dataSearchParameter.getJabatanKode() != null) {
            criteria.createAlias("jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jb.code", dataSearchParameter.getJabatanKode(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getJabatanName() != null) {
            criteria.createAlias("jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jb.name", dataSearchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getNIK() != null) {
            criteria.add(Restrictions.like("nik", dataSearchParameter.getNIK(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getName() != null) {
            criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", dataSearchParameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", dataSearchParameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
    }
}
