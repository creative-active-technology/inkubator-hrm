package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempUploadData;

/**
 *
 * @author rizkykojek
 */
public interface PayTempUploadDataDao extends IDAO<PayTempUploadData> {

	public List<PayTempUploadData> getAllDataByPaySalaryComponentId(Long paySalaryComponentId, int firstResult, int maxResults, Order orderable);
	
	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId);
	
	public PayTempUploadData getEntityByPkWithDetail(Long id);
	
}
