package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Document;
import com.inkubator.hrm.service.DocumentService;
import com.inkubator.hrm.web.search.DocumentSearchParameter;

public class DocumentLazyDataModel extends LazyDataModel<Document> implements Serializable{
	private static final Logger LOGGER = Logger.getLogger(DocumentLazyDataModel.class);
	private final DocumentSearchParameter searchParameter;
	private final DocumentService documentService;
	private List<Document> documentList = new ArrayList<Document>();
	private Integer total;
	
	public DocumentLazyDataModel(DocumentSearchParameter searchParameter, DocumentService documentService){
		this.searchParameter = searchParameter;
		this.documentService = documentService;
	}
	
	@Override
	public List<Document> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
		LOGGER.info("Step load lazy data model");
		
		try{
			Order order = null;
			if(sortField != null){
				order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
			} else {
				order = Order.desc("name");
			}
			documentList = documentService.getByParam(searchParameter, first, pageSize, order);
			total =  Integer.parseInt(String.valueOf(documentService.getTotalByParam(searchParameter)));
		} catch (Exception ex){
			LOGGER.error("Error ", ex);
		}
		
		LOGGER.info("Success load lazy data model");
		
		setPageSize(pageSize);
		setRowCount(total);
		return documentList;
	}
	
	@Override
	public Object getRowKey(Document document){
		return document.getId();
	}
	
	@Override
	public Document getRowData(String id){
		for(Document document : documentList){
			if(id.equals(String.valueOf(document.getId()))){
				return document;
			}
		}
		return null;
	}
	
	@Override
	public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
	
}
