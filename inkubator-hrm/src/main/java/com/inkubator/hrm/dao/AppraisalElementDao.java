package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalElement;

/**
*
* @author Taufik hidayat
*/
public interface AppraisalElementDao extends IDAO<AppraisalElement> {

	public List<AppraisalElement> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalAppraisalElementByParam(String parameter);
        
}
