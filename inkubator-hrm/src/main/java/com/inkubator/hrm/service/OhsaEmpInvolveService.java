package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OhsaEmpInvolve;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface OhsaEmpInvolveService extends IService<OhsaEmpInvolve> {
    
    public Long getTotalEmpInvolveByIdOhsaIncident(Long idOhsaIncident);
    
    public List<OhsaEmpInvolve> getListByOhsaIncidentId(Long ohsaIncidentId);
    
    public OhsaEmpInvolve getEntityByPKWithDetail(Long id);
    
}
