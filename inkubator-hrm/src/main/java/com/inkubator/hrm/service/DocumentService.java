package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Document;
import com.inkubator.hrm.web.search.DocumentSearchParameter;

public interface DocumentService extends IService<Document>{
	public List<Document> getByParam(DocumentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
	
	public Long getTotalByParam(DocumentSearchParameter searchParameter) throws Exception;
	
	public void save(Document entity, UploadedFile documentFile) throws Exception;
	
	public void update(Document entity, UploadedFile documentFile) throws Exception;
}
