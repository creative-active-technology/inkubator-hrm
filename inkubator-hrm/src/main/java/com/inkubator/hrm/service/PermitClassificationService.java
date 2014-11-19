package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.web.search.PermitClassificationSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface PermitClassificationService extends IService<PermitClassification> {

	public List<PermitClassification> getByParam(PermitClassificationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(PermitClassificationSearchParameter parameter) throws Exception;
        
        public PermitClassification getEntityByPKWithDetail(Long id) throws Exception;

        public void save(PermitClassification entity, List<ApprovalDefinition> appDefs) throws Exception;
	
	public void update(PermitClassification entity, List<ApprovalDefinition> appDefs) throws Exception;
        
        public PermitClassification getEntityByPkFetchApprovalDefinition(Long id) throws Exception;
}
