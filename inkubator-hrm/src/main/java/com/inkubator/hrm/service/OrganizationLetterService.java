package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OrganizationLetter;

/**
*
* @author rizkykojek
*/
public interface OrganizationLetterService extends IService<OrganizationLetter> {

	public List<OrganizationLetter> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(String parameter) throws Exception;

}
