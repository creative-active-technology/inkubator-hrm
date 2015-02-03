/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "empCareerHistoryDao")
@Lazy
public class EmpCareerHistoryDaoImpl extends IDAOImpl<EmpCareerHistory> implements EmpCareerHistoryDao {

    @Override
    public Class<EmpCareerHistory> getEntityClass() {
        return EmpCareerHistory.class;
    }

    @Override
    public List<EmpCareerHistory> getEmployeeCareerByBioId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bio.id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.addOrder(Order.asc("tglPenganngkatan"));
        return criteria.list();
    }

    @Override
    public EmpCareerHistory getByBioIdandStatus(long id, String status) {
        DetachedCriteria maxEvaluationScore = DetachedCriteria.forClass(getEntityClass())
                .setProjection(Property.forName("createdOn").max())
                .createAlias("bioData", "bio", JoinType.INNER_JOIN)
                .add(Restrictions.eq("bio.id", id))
                .add(Restrictions.eq("status", status));

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bio.id", id));
        criteria.add(Restrictions.eq("status", status));
        criteria.add(Property.forName("createdOn").eq(maxEvaluationScore));
        return (EmpCareerHistory) criteria.uniqueResult();
    }
    
     private void doSearchEmpRotasiByParamReport(ReportEmpMutationParameter searchParameter, Criteria criteria) {
         if (searchParameter.getStartDate() != null && searchParameter.getEndDate()!= null) {   
             Conjunction conjunction = Restrictions.conjunction();
             conjunction.add(Restrictions.ge("tglPenganngkatan", searchParameter.getStartDate()));
             conjunction.add(Restrictions.le("tglPenganngkatan", searchParameter.getEndDate()));
             criteria.add(conjunction);
        }
    }
    
    @Override
    public List<EmpCareerHistory> getByParamReport(ReportEmpMutationParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);       
        criteria.setProjection(Projections.max("tglPenganngkatan"));
        criteria.setProjection(Projections.groupProperty("nik"));
        doSearchEmpRotasiByParamReport(searchParameter, criteria);
   
        return criteria.list();
    }

    @Override
    public Long getTotalEmpCareerHistoryDataByParamReport(ReportEmpMutationParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpRotasiByParamReport(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
