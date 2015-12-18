package com.inkubator.hrm.web.appraisal;

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
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.service.AppraisalCompetencyUnitService;
import com.inkubator.hrm.web.lazymodel.CompetencyUnitLazyDataModel;
import com.inkubator.hrm.web.search.CompetencyUnitSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "competencyUnitViewController")
@ViewScoped
public class CompetencyUnitViewController extends BaseController {

    private CompetencyUnitSearchParameter parameter;
    private LazyDataModel<AppraisalCompetencyUnit> lazyData;
    private AppraisalCompetencyUnit selected;
    @ManagedProperty(value = "#{appraisalCompetencyUnitService}")
    private AppraisalCompetencyUnitService appraisalCompetencyUnitService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new CompetencyUnitSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	appraisalCompetencyUnitService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.appraisalCompetencyUnitService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
        	appraisalCompetencyUnitService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }
    
    public String doDetail(){
    	return "/protected/appraisal/competency_unit_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 560);
        options.put("contentHeight", 440);
        RequestContext.getCurrentInstance().openDialog("competency_unit_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

	public CompetencyUnitSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(CompetencyUnitSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<AppraisalCompetencyUnit> getLazyData() {
		if(lazyData == null){
			lazyData = new CompetencyUnitLazyDataModel(parameter, appraisalCompetencyUnitService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<AppraisalCompetencyUnit> lazyData) {
		this.lazyData = lazyData;
	}

	public AppraisalCompetencyUnit getSelected() {
		return selected;
	}

	public void setSelected(AppraisalCompetencyUnit selected) {
		this.selected = selected;
	}

	public AppraisalCompetencyUnitService getAppraisalCompetencyUnitService() {
		return appraisalCompetencyUnitService;
	}

	public void setAppraisalCompetencyUnitService(AppraisalCompetencyUnitService appraisalCompetencyUnitService) {
		this.appraisalCompetencyUnitService = appraisalCompetencyUnitService;
	}

}
