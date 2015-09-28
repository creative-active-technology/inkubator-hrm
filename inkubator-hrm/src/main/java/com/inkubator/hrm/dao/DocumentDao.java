package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Document;
import com.inkubator.hrm.web.search.DocumentSearchParameter;

public interface DocumentDao extends IDAO<Document>{
	public List<Document> getByParam(DocumentSearchParameter searchParameter, int firstResult, int maxResults, Order order);
	
	public Long getTotalByParam(DocumentSearchParameter searchParameter);
}
