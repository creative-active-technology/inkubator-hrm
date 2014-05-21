package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.web.lazymodel.FamilyRelationLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "familyRelationViewController")
@ViewScoped
public class FamilyRelationViewController extends BaseController {

    private String parameter;
    private LazyDataModel<FamilyRelation> lazyDataFamilyRelation;
    private FamilyRelation selectedFamilyRelation;
    @ManagedProperty(value = "#{familyRelationService}")
    private FamilyRelationService familyRelationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        familyRelationService = null;
        parameter = null;
        lazyDataFamilyRelation = null;
        selectedFamilyRelation = null;
    }

    public void setFamilyRelationService(FamilyRelationService familyRelationService) {
        this.familyRelationService = familyRelationService;
    }

    

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<FamilyRelation> getLazyDataFamilyRelation() {
        if (lazyDataFamilyRelation == null) {
            lazyDataFamilyRelation = new FamilyRelationLazyDataModel(parameter, familyRelationService);
        }
        return lazyDataFamilyRelation;
    }

    public void setLazyDataFamilyRelation(LazyDataModel<FamilyRelation> lazyDataFamilyRelation) {
        this.lazyDataFamilyRelation = lazyDataFamilyRelation;
    }

    public FamilyRelation getSelectedFamilyRelation() {
        return selectedFamilyRelation;
    }

    public void setSelectedFamilyRelation(FamilyRelation selectedFamilyRelation) {
        this.selectedFamilyRelation = selectedFamilyRelation;
    }

    public void doSearch() {
        lazyDataFamilyRelation = null;
    }

    public void doDelete() {
        try {
            familyRelationService.delete(selectedFamilyRelation);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete religion ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete religion", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedFamilyRelation.getId()));
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
        RequestContext.getCurrentInstance().openDialog("family_relation_form", options, params);
    }

    public void onDialogClose(SelectEvent event) {
        //re-calculate searching
        doSearch();

        //show growl message
        String condition = (String) event.getObject();
        if (condition.equalsIgnoreCase(HRMConstant.SAVE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } else if (condition.equalsIgnoreCase(HRMConstant.UPDATE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
    
    
    public void onDelete(){
          try {
            selectedFamilyRelation = this.familyRelationService.getEntiyByPK(selectedFamilyRelation.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
     public void doDetail(){
          try {
            selectedFamilyRelation = this.familyRelationService.getEntiyByPK(selectedFamilyRelation.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
