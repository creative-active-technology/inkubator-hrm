/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OhsaEmpInvolveDao;
import com.inkubator.hrm.entity.OhsaEmpInvolve;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "ohsaEmpInvolveDao")
@Lazy
public class OhsaEmpInvolveDaoImpl extends IDAOImpl<OhsaEmpInvolve> implements OhsaEmpInvolveDao {

    @Override
    public Class<OhsaEmpInvolve> getEntityClass() {
        return OhsaEmpInvolve.class;
    }

    @Override
    public Long getTotalEmpInvolveByIdOhsaIncident(Long idOhsaIncident) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("ohsaIncident", FetchMode.JOIN); 
        criteria.add(Restrictions.eq("ohsaIncident.id", idOhsaIncident));
        
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<OhsaEmpInvolve> getListByOhsaIncidentId(Long ohsaIncidentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("ohsaIncident", FetchMode.JOIN); 
        criteria.setFetchMode("empData", FetchMode.JOIN);         
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN); 
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN); 
        criteria.add(Restrictions.eq("ohsaIncident.id", ohsaIncidentId));
        return criteria.list();
    }

    @Override
    public OhsaEmpInvolve getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("ohsaIncident", FetchMode.JOIN); 
        criteria.setFetchMode("empData", FetchMode.JOIN);         
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN); 
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);      
        criteria.add(Restrictions.eq("id", id));
        return (OhsaEmpInvolve) criteria.uniqueResult();
    }
    
}
