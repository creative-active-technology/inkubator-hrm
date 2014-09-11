package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalDetail;

/**
*
* @author Taufik Hidayat
*/
public interface AppraisalDetailService extends IService<AppraisalDetail> {

	public List<AppraisalDetail> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalAppraisalDetailByParam(String parameter) throws Exception;
        
        public AppraisalDetail getEntityByPKWithDetail(Long id) throws Exception;

}
