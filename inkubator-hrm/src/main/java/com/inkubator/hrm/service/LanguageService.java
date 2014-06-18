package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LanguageUsed;

/**
*
* @author Taufik Hidayat
*/
public interface LanguageService extends IService<LanguageUsed> {

	public List<LanguageUsed> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalLanguageByParam(String parameter) throws Exception;

}
