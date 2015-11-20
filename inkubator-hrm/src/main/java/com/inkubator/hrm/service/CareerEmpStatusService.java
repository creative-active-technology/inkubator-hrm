package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.web.search.CareerEmpStatusSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface CareerEmpStatusService extends IService<CareerEmpStatus> {

	public List<CareerEmpStatus> getByParam(CareerEmpStatusSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(CareerEmpStatusSearchParameter parameter);
	
	public CareerEmpStatus getEntityByPkWithDetail(Long id);
	
}
