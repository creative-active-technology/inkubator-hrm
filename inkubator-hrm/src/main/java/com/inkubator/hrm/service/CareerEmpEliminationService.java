package com.inkubator.hrm.service;


import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;

public interface CareerEmpEliminationService extends IService<CareerEmpElimination>{
public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) throws Exception;
}
