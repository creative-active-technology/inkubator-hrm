package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Faculty;

/**
*
* @author Taufik Hidayat
*/
public interface FacultyService extends IService<Faculty> {

	public List<Faculty> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalFacultyByParam(String parameter) throws Exception;

}
