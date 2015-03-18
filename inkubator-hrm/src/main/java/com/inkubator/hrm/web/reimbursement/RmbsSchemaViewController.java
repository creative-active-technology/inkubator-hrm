package com.inkubator.hrm.web.reimbursement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.hrm.web.lazymodel.RmbsSchemaLazyDataModel;
import com.inkubator.hrm.web.search.RmbsSchemaSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaViewController")
@ViewScoped
public class RmbsSchemaViewController extends BaseController {

    private RmbsSchemaSearchParameter parameter;
    private LazyDataModel<RmbsSchema> lazyData;
    private RmbsSchema selected;
    @ManagedProperty(value = "#{rmbsSchemaService}")
    private RmbsSchemaService rmbsSchemaService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new RmbsSchemaSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsSchemaService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
        return "/protected/reimbursement/rmbs_schema_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public String doAdd() {
        return "/protected/reimbursement/rmbs_schema_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/reimbursement/rmbs_schema_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void doSelectEntity() {
        try {
            selected = this.rmbsSchemaService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
        	rmbsSchemaService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }

	public RmbsSchemaSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(RmbsSchemaSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RmbsSchema> getLazyData() {
		if(lazyData == null){
			lazyData = new RmbsSchemaLazyDataModel(parameter, rmbsSchemaService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RmbsSchema> lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsSchema getSelected() {
		return selected;
	}

	public void setSelected(RmbsSchema selected) {
		this.selected = selected;
	}

	public RmbsSchemaService getRmbsSchemaService() {
		return rmbsSchemaService;
	}

	public void setRmbsSchemaService(RmbsSchemaService rmbsSchemaService) {
		this.rmbsSchemaService = rmbsSchemaService;
	}
    
}
