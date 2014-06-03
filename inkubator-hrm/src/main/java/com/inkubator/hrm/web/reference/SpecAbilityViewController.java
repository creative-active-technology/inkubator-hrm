package com.inkubator.hrm.web.reference;


import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.service.SpecificationAbilityService;
import com.inkubator.hrm.web.lazymodel.SpecificationAbilityLazyDataModel;
import com.inkubator.hrm.web.search.SpecificationAbilitySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "specAbilityViewController")
@ViewScoped
public class SpecAbilityViewController extends BaseController {

    private SpecificationAbilitySearchParameter searchParameter;
    private LazyDataModel<SpecificationAbility> lazyDataSpecificationAbility;
    private SpecificationAbility selectedSpecificationAbility;
    @ManagedProperty(value = "#{specificationAbilityService}")
    private SpecificationAbilityService specificationAbilityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new SpecificationAbilitySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        specificationAbilityService = null;
        searchParameter = null;
        lazyDataSpecificationAbility = null;
        selectedSpecificationAbility = null;
    }

    public LazyDataModel<SpecificationAbility> getLazyDataSpecificationAbility() {
        if (lazyDataSpecificationAbility == null) {
            lazyDataSpecificationAbility = new SpecificationAbilityLazyDataModel(searchParameter, specificationAbilityService);
        }
        return lazyDataSpecificationAbility;
    }

    public void setLazyDataSpecificationAbility(
            LazyDataModel<SpecificationAbility> lazyDataSpecificationAbility) {
        this.lazyDataSpecificationAbility = lazyDataSpecificationAbility;
    }

    public SpecificationAbility getSelectedSpecificationAbility() {
        return selectedSpecificationAbility;
    }

    public void setSelectedSpecificationAbility(SpecificationAbility selectedSpecificationAbility) {
        this.selectedSpecificationAbility = selectedSpecificationAbility;
    }

    public void setSpecificationAbilityService(SpecificationAbilityService specificationAbilityService) {
        this.specificationAbilityService = specificationAbilityService;
    }

    public SpecificationAbilitySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(SpecificationAbilitySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public void doSearch() {
        lazyDataSpecificationAbility = null;
    }

    public void doDetail() {
        try {
            selectedSpecificationAbility = this.specificationAbilityService.getEntiyByPK(selectedSpecificationAbility.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            specificationAbilityService.delete(selectedSpecificationAbility);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete specificationAbility ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete specificationAbility", ex);
        }
    }

    public String doAdd() {
        return "/protected/reference/spec_ability_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/reference/spec_ability_form.htm?faces-redirect=true&execution=e" + selectedSpecificationAbility.getId();
    }

   
}
