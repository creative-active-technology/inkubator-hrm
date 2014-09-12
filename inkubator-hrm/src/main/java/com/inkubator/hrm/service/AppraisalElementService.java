package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalElement;

/**
*
* @author Taufik Hidayat
*/
public interface AppraisalElementService extends IService<AppraisalElement> {

	public List<AppraisalElement> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalAppraisalElementByParam(String parameter) throws Exception;

}
