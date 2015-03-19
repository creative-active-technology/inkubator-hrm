/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OhsaIncidentDocumentDao;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.entity.OhsaIncidentDocument;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "ohsaIncidentDocumentDao")
@Lazy
public class OhsaIncidentDocumentDaoImpl extends IDAOImpl<OhsaIncidentDocument> implements OhsaIncidentDocumentDao {

    @Override
    public Class<OhsaIncidentDocument> getEntityClass() {
        return OhsaIncidentDocument.class;
    }

    @Override
    public List<OhsaIncidentDocument> getListByOhsaIncidentId(Long ohsaIncidentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("ohsaIncident", FetchMode.JOIN); 
        criteria.add(Restrictions.eq("ohsaIncident.id", ohsaIncidentId));
        return criteria.list();
    }

    @Override
    public OhsaIncidentDocument getEntityByPKWithDetail(Integer id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("ohsaIncident", FetchMode.JOIN);       
        criteria.add(Restrictions.eq("id", id));
        return (OhsaIncidentDocument) criteria.uniqueResult();
    }
    
}
