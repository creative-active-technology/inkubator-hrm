/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtFingerExceptionDao;
import com.inkubator.hrm.entity.WtFingerException;
import com.inkubator.hrm.web.search.WtFingerExceptionSearchParameter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
 * @author Deni
 */
@Repository(value = "wtFingerExceptionDao")
@Lazy
public class WtFingerExceptionDaoImpl extends IDAOImpl<WtFingerException> implements WtFingerExceptionDao {

    @Override
    public Class<WtFingerException> getEntityClass() {
        return WtFingerException.class;
    }

    @Override
    public List<WtFingerException> getByParamWithDetail(WtFingerExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.employeeType", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalWtFingerExceptionByParam(WtFingerExceptionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public WtFingerException getEntityByParamWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.employeeType", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (WtFingerException) criteria.uniqueResult();
    }

    @Override
    public void saveBatch(List<WtFingerException> data) {
        int counter = 0;
        for (WtFingerException wtFingerException : data) {
            getCurrentSession().save(wtFingerException);
           
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }
    
    private void doSearch(WtFingerExceptionSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
        criteria.createAlias("ed.bioData", "bio", JoinType.INNER_JOIN);
        criteria.createAlias("ed.jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getEmpData())) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", searchParameter.getEmpData(), MatchMode.START));
            disjunction.add(Restrictions.like("bio.lastName", searchParameter.getEmpData(), MatchMode.START));
            criteria.add(disjunction);
        }
        if (searchParameter.getNik()!= null) {
            criteria.add(Restrictions.like("ed.nik", searchParameter.getNik(), MatchMode.START));
        }
        if(searchParameter.getNamaJabatan() != null){
            criteria.add(Restrictions.like("jabatan.name", searchParameter.getNamaJabatan(), MatchMode.START));
        }
        if(searchParameter.getCodeJabatan()!= null){
            criteria.add(Restrictions.like("jabatan.code", searchParameter.getCodeJabatan(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public WtFingerException getEntityByEmpDataId(Long empDataid) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataid));
        return (WtFingerException) criteria.uniqueResult();
	}
    
}
