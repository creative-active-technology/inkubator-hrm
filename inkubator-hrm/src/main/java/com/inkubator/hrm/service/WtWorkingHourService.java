package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.web.search.WorkingHourSearchParameter;

/**
*
* @author rizkykojek
*/
public interface WtWorkingHourService extends IService<WtWorkingHour> {

	public List<WtWorkingHour> getByParam(WorkingHourSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(WorkingHourSearchParameter parameter) throws Exception;

	public WtWorkingHour getEntityByPkWithDetail(long id);

	public List<WtWorkingHour> getAllDataExceptId(long id);

}
