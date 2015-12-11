package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CareerEmpEliminationDao;
import com.inkubator.hrm.entity.CareerEmpElimination;

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

}
