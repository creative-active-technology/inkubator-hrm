/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempUploadData;

/**
 *
 * @author rizkykojek
 */
public interface PayTempUploadDataService extends IService<PayTempUploadData> {
	
	public List<PayTempUploadData> getAllDataByPaySalaryComponentId(Long paySalaryComponentId, int firstResult, int maxResults, Order orderable);
	
	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId);
	
	public PayTempUploadData getEntityByPkWithDetail(Long id);
}
