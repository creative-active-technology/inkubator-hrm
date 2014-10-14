package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LeaveImplementationDao extends IDAO<LeaveImplementation> {

	public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(LeaveImplementationSearchParameter parameter);
	
	public Loan getEntityByPkWithDetail(Long id);
}
