package com.inkubator.hrm.web.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Document;
import com.inkubator.hrm.service.DocumentService;
import com.inkubator.hrm.web.lazymodel.DocumentLazyDataModel;
import com.inkubator.hrm.web.search.DocumentSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;


@ManagedBean(name = "documentViewController")
@ViewScoped
public class DocumentViewController extends BaseController{
	@ManagedProperty(value = "#{documentService}")
	private DocumentService service;
	private DocumentSearchParameter searchParameter;
	private LazyDataModel<Document> lazy;
	private Document selected;
	
	@PostConstruct
	@Override
	public void initialization(){
		super.initialization();
		searchParameter = new DocumentSearchParameter();
	}
	
	@PreDestroy
    private void cleanAndExit() {
        searchParameter=null;
        lazy=null;
        service=null;
        selected=null;
    }
	
	public void doSearch(){
		lazy = null;
	}
	
	public void doSelectEntity(){
		try{
			selected = this.service.getEntiyByPK(selected.getId());
		} catch(Exception ex){
			LOGGER.error("Error", ex);
		}
	}
	
	public void doDelete() {
        try {
            this.service.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
	
	public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 460);
        RequestContext.getCurrentInstance().openDialog("document_form", options, params);
    }
	
	public void doAdd(){
		showDialog(null);
	}
	
	public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("documentId", dataIsi);
        showDialog(dataToSend);
    }
	
	@Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
    
     public void onDelete() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public DocumentService getService() {
		return service;
	}

	public void setService(DocumentService service) {
		this.service = service;
	}

	public DocumentSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(DocumentSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<Document> getLazy() {
		if(lazy == null){
            lazy = new DocumentLazyDataModel(searchParameter, service);
        }
		return lazy;
	}

	public void setLazy(LazyDataModel<Document> lazy) {
		this.lazy = lazy;
	}

	public Document getSelected() {
		return selected;
	}

	public void setSelected(Document selected) {
		this.selected = selected;
	}
     
    
}
