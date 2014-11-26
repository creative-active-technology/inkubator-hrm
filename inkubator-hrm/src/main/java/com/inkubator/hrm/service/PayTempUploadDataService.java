/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface PayTempUploadDataService extends IService<PayTempUploadData> {
	
	public List<PayTempUploadData> getAllDataByParam(PayTempUploadDataSearchParameter parameter, int firstResult, int maxResults, Order orderable);
	
	public Long getTotalByParam(PayTempUploadDataSearchParameter parameter);
	
	public PayTempUploadData getEntityByPkWithDetail(Long id);

	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId);

	public Double getTotalSalaryByPaySalaryComponentId(Long paySalaryComponentId);
}
