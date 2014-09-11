package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PublicHoliday;

/**
*
* @author Taufik Hidayat
*/
public interface PublicHolidayDao extends IDAO<PublicHoliday> {

	public List<PublicHoliday> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalPublicHolidayByParam(String parameter);
        
        public PublicHoliday getEntityByPKWithDetail(Long id);
        
        public List<PublicHoliday> getAllWithDetail();

}
