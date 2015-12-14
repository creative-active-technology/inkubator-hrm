package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CareerEmpEliminationDao;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Repository(value="careerEmpEliminationDao")
@Lazy
public class CareerEmpEliminationDaoImpl extends IDAOImpl<CareerEmpElimination> implements CareerEmpEliminationDao {

	@Override
	public Class<CareerEmpElimination> getEntityClass() {
		return CareerEmpElimination.class;
	}

	@Override
	public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
