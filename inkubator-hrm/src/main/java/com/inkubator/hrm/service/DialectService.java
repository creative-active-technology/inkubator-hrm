package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.web.search.DialectSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface DialectService extends IService<Dialect> {

	public List<Dialect> getByParam(DialectSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(DialectSearchParameter parameter) throws Exception;

}
