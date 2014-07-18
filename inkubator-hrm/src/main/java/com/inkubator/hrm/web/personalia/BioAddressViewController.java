package com.inkubator.hrm.web.personalia;

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
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.web.search.BioAddressSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "bioAddressViewController")
@ViewScoped
public class BioAddressViewController extends BaseController {

    private BioAddressSearchParameter parameter;
    private LazyDataModel<BioAddress> lazyDataBioAddress;
    private BioAddress selectedBioAddress;
    @ManagedProperty(value = "#{bioAddressService}")
    private BioAddressService bioAddressService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        bioAddressService = null;
        parameter = null;
        lazyDataBioAddress = null;
        selectedBioAddress = null;
    }    

    public BioAddressSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(BioAddressSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<BioAddress> getLazyDataBioAddress() {
		return lazyDataBioAddress;
	}

	public void setLazyDataBioAddress(LazyDataModel<BioAddress> lazyDataBioAddress) {
		this.lazyDataBioAddress = lazyDataBioAddress;
	}

	public BioAddress getSelectedBioAddress() {
		return selectedBioAddress;
	}

	public void setSelectedBioAddress(BioAddress selectedBioAddress) {
		this.selectedBioAddress = selectedBioAddress;
	}

	public void setBioAddressService(BioAddressService bioAddressService) {
		this.bioAddressService = bioAddressService;
	}

	public void doSearch() {
        lazyDataBioAddress = null;
    }

    public void doDetail() {
        try {
            selectedBioAddress = this.bioAddressService.getEntiyByPK(selectedBioAddress.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            bioAddressService.delete(selectedBioAddress);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete bioAddress ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioAddress", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedBioAddress.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        RequestContext.getCurrentInstance().openDialog("bio_address_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
