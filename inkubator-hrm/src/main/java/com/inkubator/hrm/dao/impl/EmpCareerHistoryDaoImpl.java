/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.common.util.DateTimeUtil;
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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
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
        doSearchEmpRotasiByParamReport(searchParameter, criteria);
        
        DetachedCriteria maxTglPengangkatanQuery = DetachedCriteria.forClass(getEntityClass());
        ProjectionList proj = Projections.projectionList();
        proj.add(Projections.max("tglPenganngkatan"));
        proj.add(Projections.groupProperty("nik"));
        maxTglPengangkatanQuery.setProjection(proj);        
       
        criteria.add(Subqueries.propertiesIn(new String[] {"tglPenganngkatan","nik"}, maxTglPengangkatanQuery));
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        
        List<EmpCareerHistory> listEmpCareerHistorys = criteria.list(); 
        
        //Set Jabatan Lama/sebelumnya dari masing - masing record
        for(EmpCareerHistory ech : listEmpCareerHistorys){
             Criteria criteriaOldPosition = getCurrentSession().createCriteria(getEntityClass());
             criteriaOldPosition.setFetchMode("jabatan", FetchMode.JOIN);
             criteriaOldPosition.add(Restrictions.eq("nik", ech.getNik()));
             criteriaOldPosition.add(Restrictions.lt("tglPenganngkatan", ech.getTglPenganngkatan()));
             criteriaOldPosition.addOrder(Order.desc("tglPenganngkatan"));
             criteriaOldPosition.setMaxResults(1);
             EmpCareerHistory prevPosition = (EmpCareerHistory) criteriaOldPosition.uniqueResult();
             
             //jika sebelumnya dia sudah pernah menjabat di posisi lain maka set oldJabatan dengan posisi tersebut
             if(null != prevPosition){
                  ech.setJabatanOldCode(prevPosition.getJabatan().getCode());
                 ech.setJabatanOldName(prevPosition.getJabatan().getName());                
             }else{
                 ech.setJabatanOldCode("-");
             }
        }        
        
        return listEmpCareerHistorys;
    }

    @Override
    public Long getTotalEmpCareerHistoryDataByParamReport(ReportEmpMutationParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpRotasiByParamReport(searchParameter, criteria);
        
        DetachedCriteria maxTglPengangkatanQuery = DetachedCriteria.forClass(getEntityClass());
        ProjectionList proj = Projections.projectionList();
        proj.add(Projections.max("tglPenganngkatan"));
        proj.add(Projections.groupProperty("nik"));
        maxTglPengangkatanQuery.setProjection(proj);
       
        criteria.add(Subqueries.propertiesIn(new String[] {"tglPenganngkatan","nik"}, maxTglPengangkatanQuery));
        
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
