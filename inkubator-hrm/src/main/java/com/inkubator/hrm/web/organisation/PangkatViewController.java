package com.inkubator.hrm.web.organisation;

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
import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.service.PangkatService;
import com.inkubator.hrm.web.lazymodel.PangkatLazyDataModel;
import com.inkubator.hrm.web.search.PangkatSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "pangkatViewController")
@ViewScoped
public class PangkatViewController extends BaseController {
    
    private PangkatSearchParameter parameter;
    private LazyDataModel<Pangkat> lazyDataPangkat;
    private Pangkat selectedPangkat;
    @ManagedProperty(value = "#{pangkatService}")
    private PangkatService pangkatService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new PangkatSearchParameter();
    }
    
    @PreDestroy
    public void cleanAndExit() {
        pangkatService = null;
        parameter = null;
        lazyDataPangkat = null;
        selectedPangkat = null;
    }
    
    public PangkatSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(PangkatSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<Pangkat> getLazyDataPangkat() {
		if(lazyDataPangkat == null){
			lazyDataPangkat = new PangkatLazyDataModel(parameter, pangkatService);
		}
		return lazyDataPangkat;
	}

	public void setLazyDataPangkat(LazyDataModel<Pangkat> lazyDataPangkat) {
		this.lazyDataPangkat = lazyDataPangkat;
	}

	public Pangkat getSelectedPangkat() {
		return selectedPangkat;
	}

	public void setSelectedPangkat(Pangkat selectedPangkat) {
		this.selectedPangkat = selectedPangkat;
	}

	public void setPangkatService(PangkatService pangkatService) {
		this.pangkatService = pangkatService;
	}

	public void doSearch() {
        lazyDataPangkat = null;
    }
    
    public void doDetail() {
        try {
            selectedPangkat = this.pangkatService.getEntiyByPK(selectedPangkat.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }    
    
    public void doDelete() {
        try {
            pangkatService.delete(selectedPangkat);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete organizationLetter ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete organizationLetter", ex);
        }
    }
    
    public void doAdd() {
        showDialog(null);
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedPangkat.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 350);
        options.put("contentHeight", 300);
        RequestContext.getCurrentInstance().openDialog("position_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
