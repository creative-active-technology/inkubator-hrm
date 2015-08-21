/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Insurance;
import com.inkubator.hrm.web.search.InsuranceSearchParameter;

/**
 *
 * @author Deni
 */
public interface InsuranceDao extends IDAO<Insurance>{
	public List<Insurance> getByParam(InsuranceSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(InsuranceSearchParameter parameter);
	
	public Insurance getEntityByPkWithDetail(Long id);
}
