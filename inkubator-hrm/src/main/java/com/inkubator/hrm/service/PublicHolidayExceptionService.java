package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PublicHolidayException;
import com.inkubator.hrm.web.search.PublicHolidayExceptionSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface PublicHolidayExceptionService extends IService<PublicHolidayException> {

	public List<PublicHolidayException> getByParam(PublicHolidayExceptionSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(PublicHolidayExceptionSearchParameter parameter) throws Exception;
        
        public PublicHolidayException getEntityByPKWithDetail(Long id) throws Exception;

}
