/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.web.search.PermitDistributionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Taufik
 */
public interface PermitDistributionService extends IService<PermitDistribution> {

    public List<PermitDistribution> getByParamWithDetail(PermitDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalPermitDistributionByParam(PermitDistributionSearchParameter searchParameter) throws Exception;

    public void saveMassPenempatanPermit(List<EmpData> data, long leaveId, double starBalance) throws Exception;

    public PermitDistribution getEntityByParamWithDetail(Long empId) throws Exception;

    public List<PermitDistribution> getAllDataByEmpIdWithDetail() throws Exception;
    
    public List<PermitDistribution> getAllDataByEmpIdFetchPermit(Long empDataId) throws Exception;
    
    public PermitDistribution getEntityByPermitClassificationIdAndEmpDataId(Long leaveId, Long empDataId) throws Exception;
}
