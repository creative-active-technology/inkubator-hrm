package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Major;

/**
*
* @author Taufik Hidayat
*/
public interface MajorService extends IService<Major> {

	public List<Major> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalMajorByParam(String parameter) throws Exception;

        public Major getEntityByPkWithDetail(Long id) throws Exception;
}
