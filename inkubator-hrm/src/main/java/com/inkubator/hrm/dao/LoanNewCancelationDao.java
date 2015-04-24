package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewCancelation;
import com.inkubator.hrm.web.model.LoanNewCancelationBoxViewModel;
import com.inkubator.hrm.web.search.LoanNewCancelationBoxSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LoanNewCancelationDao extends IDAO<LoanNewCancelation> {

    public Long getCurrentMaxId();
    
    public List<LoanNewCancelationBoxViewModel> getByParam(LoanNewCancelationBoxSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(LoanNewCancelationBoxSearchParameter parameter);
	
	
}
