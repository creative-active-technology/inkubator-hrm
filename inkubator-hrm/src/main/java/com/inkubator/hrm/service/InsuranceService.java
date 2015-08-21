/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Insurance;
import com.inkubator.hrm.web.search.InsuranceSearchParameter;

/**
 *
 * @author Deni
 */
public interface InsuranceService extends IService<Insurance>{
	public List<Insurance> getByParam(InsuranceSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(InsuranceSearchParameter parameter) throws Exception;
	
	public Insurance getEntityByPkWithDetail(Long id) throws Exception;
}
