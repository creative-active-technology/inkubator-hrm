package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Language;

/**
*
* @author Taufik Hidayat
*/
public interface LanguageService extends IService<Language> {

	public List<Language> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalLanguageByParam(String parameter) throws Exception;

}
