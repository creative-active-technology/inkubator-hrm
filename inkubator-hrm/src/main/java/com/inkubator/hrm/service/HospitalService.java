package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.web.search.HospitalSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface HospitalService extends IService<Hospital> {

	public List<Hospital> getByParam(HospitalSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(HospitalSearchParameter parameter) throws Exception;
        
        public Hospital getEntityByPKWithDetail(Long id) throws Exception;

}
