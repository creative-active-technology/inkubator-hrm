package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PublicHoliday;

/**
*
* @author Taufik Hidayat
*/
public interface PublicHolidayService extends IService<PublicHoliday> {

	public List<PublicHoliday> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalPublicHolidayByParam(String parameter) throws Exception;
        
        public PublicHoliday getEntityByPKWithDetail(Long id) throws Exception;
        
        public List<PublicHoliday> getAllWithDetail() throws Exception;

}
