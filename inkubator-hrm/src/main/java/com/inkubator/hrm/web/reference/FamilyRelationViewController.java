package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.web.lazymodel.FamilyRelationLazyDataModel;
import com.inkubator.hrm.web.search.FamilyRelationSearchParameter;
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

    private FamilyRelationSearchParameter parameter;
    private LazyDataModel<FamilyRelation> lazyDataFamilyRelation;
    private FamilyRelation selectedFamilyRelation;
    @ManagedProperty(value = "#{familyRelationService}")
    private FamilyRelationService familyRelationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new FamilyRelationSearchParameter();
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

    

    public FamilyRelationSearchParameter getParameter() {
        return parameter;
    }

    public void setParameter(FamilyRelationSearchParameter parameter) {
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
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
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
        options.put("contentWidth", 480);
        options.put("contentHeight", 370);
        RequestContext.getCurrentInstance().openDialog("family_relation_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();

        //show growl message
      super.onDialogReturn(event);
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
