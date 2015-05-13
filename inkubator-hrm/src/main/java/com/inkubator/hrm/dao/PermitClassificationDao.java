package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.web.search.PermitClassificationSearchParameter;

/**
 * 
 * @author Taufik hidayat
 */
public interface PermitClassificationDao extends IDAO<PermitClassification> {

	public List<PermitClassification> getByParam(PermitClassificationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalPermitClassificationByParam(PermitClassificationSearchParameter parameter);

	public PermitClassification getEntityByPKWithDetail(Long id);

	public PermitClassification getEntityByPkFetchApprovalDefinition(Long id);
	
	public List<PermitClassification> getAllDataByIsActiveAndOnePerEmployee(Boolean isActive, Boolean onePerEmployee);

}
