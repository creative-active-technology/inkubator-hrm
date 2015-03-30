package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OhsaEmpInvolve;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface OhsaEmpInvolveDao extends IDAO<OhsaEmpInvolve> {

//	public List<OhsaEmpInvolve> getByParam(BankSearchParameter parameter, int firstResult, int maxResults, Order orderable);
//
//	public Long getTotalOhsaEmpInvolveByParam(BankSearchParameter parameter);
    
    public Long getTotalEmpInvolveByIdOhsaIncident(Long idOhsaIncident);
    
    public List<OhsaEmpInvolve> getListByOhsaIncidentId(Long ohsaIncidentId);
    
    public OhsaEmpInvolve getEntityByPKWithDetail(Long id);

        
}
