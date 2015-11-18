package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.web.search.CareerEmpStatusSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface CareerEmpStatusDao extends IDAO<CareerEmpStatus> {

	public List<CareerEmpStatus> getByParam(CareerEmpStatusSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(CareerEmpStatusSearchParameter parameter);
	
	public CareerEmpStatus getEntityByPkWithDetail(Long id);
	
}
