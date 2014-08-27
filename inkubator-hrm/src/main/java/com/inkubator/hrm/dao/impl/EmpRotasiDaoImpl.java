/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EmpRotasiDao;
import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.web.search.EmpRotasiSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
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
@Repository(value = "empRotasiDao")
@Lazy
public class EmpRotasiDaoImpl extends IDAOImpl<EmpRotasi> implements EmpRotasiDao {

    @Override
    public Class<EmpRotasi> getEntityClass() {
        return EmpRotasi.class;
    }

    @Override
    public List<EmpRotasi> getByParam(EmpRotasiSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpRotasiByParam(searchParameter, criteria);
        return null;
    }

    @Override
    public Long getTotalEmpRotasiDataByParam(EmpRotasiSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpRotasiByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchEmpRotasiByParam(EmpRotasiSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getNIK() != null) {
            criteria.createAlias("empData", "ep", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("ep.nik", searchParameter.getNIK(), MatchMode.ANYWHERE));
        }

        if (searchParameter.getName() != null) {
            criteria.createAlias("empData", "ep", JoinType.INNER_JOIN);
            criteria.createAlias("ep.bioData", "bio", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("bio.firstName", searchParameter.getName(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getJabatanLama() != null) {
            criteria.createAlias("jabatanByOldFunctionId", "jbo", JoinType.INNER_JOIN);
            criteria.add(Restrictions.eq("jbo.name", searchParameter.getJabatanLama()));
        }

        if (searchParameter.getJabatanBaru() != null) {
            criteria.createAlias("jabatanByNewFunctionId", "jbn", JoinType.INNER_JOIN);
            criteria.add(Restrictions.eq("jbn.name", searchParameter.getJabatanBaru()));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }
}
