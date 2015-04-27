package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.web.search.FacultySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface FacultyService extends IService<Faculty> {

	public List<Faculty> getByParam(FacultySearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalFacultyByParam(FacultySearchParameter SearchParameter) throws Exception;

        public Faculty getEntityByPkWithDetail(Long id) throws Exception;
}
