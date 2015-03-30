package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.OhsaIncidentDocument;
import com.inkubator.hrm.web.search.BankSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface OhsaIncidentDocumentDao extends IDAO<OhsaIncidentDocument> {
    
    public List<OhsaIncidentDocument> getListByOhsaIncidentId(Long ohsaIncidentId);
    
    public OhsaIncidentDocument getEntityByPKWithDetail(Integer id);
    
}
