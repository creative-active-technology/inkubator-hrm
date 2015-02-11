/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface PayTempUploadDataService extends IService<PayTempUploadData> {
	
	public List<PayTempUploadData> getAllDataByParam(PayTempUploadDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
	
	public Long getTotalByParam(PayTempUploadDataSearchParameter parameter) throws Exception;
	
	public PayTempUploadData getEntityByPkWithDetail(Long id) throws Exception;

	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId) throws Exception;

	public Double getTotalSalaryByPaySalaryComponentId(Long paySalaryComponentId) throws Exception;
	
	public void executeBatchFileUpload(PaySalaryUploadFileModel report) throws Exception;

	public String updateFileAndDeleteData(long id, UploadedFile file) throws Exception;

	public void reuse(Long paySalaryComponentId, Long periodeId) throws Exception;;
	
}
