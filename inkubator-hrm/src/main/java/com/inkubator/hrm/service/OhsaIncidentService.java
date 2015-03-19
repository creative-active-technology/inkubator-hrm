package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.web.search.OhsaIncidentSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface OhsaIncidentService extends IService<OhsaIncident> {
    public List<OhsaIncident> getByParam(OhsaIncidentSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(OhsaIncidentSearchParameter parameter);	
    
    public OhsaIncident getEntityByPKWithDetail(Long id);
}
