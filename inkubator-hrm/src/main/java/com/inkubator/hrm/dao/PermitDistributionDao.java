/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.web.search.PermitDistributionSearchParameter;

/**
 *
 * @author Taufik
 */
public interface PermitDistributionDao extends IDAO<PermitDistribution> {

    public List<PermitDistribution> getByParamWithDetail(PermitDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalPermitDistributionByParam(PermitDistributionSearchParameter searchParameter);

    public PermitDistribution getEntityByParamWithDetail(Long empId);
    
    public List<PermitDistribution> getAllDataByEmpIdWithDetail();

    public void saveBatch(List<PermitDistribution> data);

	public List<PermitDistribution> getAllDataByPermitClassificationIdAndIsActiveEmployee(Long permitClassificationId);
	
	public List<PermitDistribution> getAllDataByEndDateLessThan(Date date);

	public List<PermitDistribution> getAllDataByEmpIdFetchPermit(Long empDataId);

	public PermitDistribution getEntityByPermitClassificationIdAndEmpDataId(Long permitClassificationId, Long empDataId);
	
	public List<PermitDistribution> getAllDataByEmpDataId(Long empDataId);
}
