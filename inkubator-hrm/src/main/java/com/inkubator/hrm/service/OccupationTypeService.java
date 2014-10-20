package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OccupationType;

/**
*
* @author Taufik Hidayat
*/
public interface OccupationTypeService extends IService<OccupationType> {

	public List<OccupationType> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalOccupationTypeByParam(String parameter) throws Exception;
        
        public OccupationType getEntityByPkWithDetail(Long id) throws Exception;
}
