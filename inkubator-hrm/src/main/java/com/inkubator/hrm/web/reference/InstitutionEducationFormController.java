package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.InstitutionEducationService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.InstitutionEducationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "institutionEducationFormController")
@ViewScoped
public class InstitutionEducationFormController extends BaseController {

    private InstitutionEducationModel institutionEducationModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{institutionEducationService}")
    private InstitutionEducationService institutionEducationService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    private Map<String, Long> citys = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            institutionEducationModel = new InstitutionEducationModel();
            isUpdate = Boolean.FALSE;
            List<City> listCitys = cityService.getAllData();
            
            for (City city : listCitys) {
                citys.put(city.getCityName(), city.getId());
            }
            
            MapUtil.sortByValue(citys);
            if (StringUtils.isNumeric(param)) {
                try {
                    InstitutionEducation institutionEducation = institutionEducationService.getEntiyByPK(Long.parseLong(param));
                    if (institutionEducation != null) {
                        institutionEducationModel.setId(institutionEducation.getId());
                        institutionEducationModel.setInstitutionEducationCode(institutionEducation.getInstitutionEducationCode());
                        institutionEducationModel.setInstitutionEducationName(institutionEducation.getInstitutionEducationName());
                        institutionEducationModel.setCityId(institutionEducation.getCity().getId());
                        institutionEducationModel.setAddress(institutionEducation.getAddress());
                        institutionEducationModel.setPostalCode(institutionEducation.getPostalCode());
                        isUpdate = Boolean.TRUE;
                    }
                    
                } catch (Exception e) {
                    LOGGER.error("Error", e);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(InstitutionEducationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        institutionEducationService = null;
//        institutionEducationModel = null;
        isUpdate = null;
    }

    public InstitutionEducationModel getInstitutionEducationModel() {
        return institutionEducationModel;
    }

    public void setInstitutionEducationModel(InstitutionEducationModel institutionEducationModel) {
        this.institutionEducationModel = institutionEducationModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setInstitutionEducationService(InstitutionEducationService institutionEducationService) {
        this.institutionEducationService = institutionEducationService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    
    
    public Map<String, Long> getCitys() {
        return citys;
    }

    public void setCitys(Map<String, Long> citys) {
        this.citys = citys;
    }

    public void doSave() {
        InstitutionEducation institutionEducation = getEntityFromViewModel(institutionEducationModel);
        try {
            if (isUpdate) {
                institutionEducationService.update(institutionEducation);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                institutionEducationService.save(institutionEducation);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private InstitutionEducation getEntityFromViewModel(InstitutionEducationModel institutionEducationModel) {
        InstitutionEducation institutionEducation = new InstitutionEducation();
        if (institutionEducationModel.getId() != null) {
            institutionEducation.setId(institutionEducationModel.getId());
        }
        institutionEducation.setInstitutionEducationCode(institutionEducationModel.getInstitutionEducationCode());
        institutionEducation.setInstitutionEducationName(institutionEducationModel.getInstitutionEducationName());
        institutionEducation.setCity(new City(institutionEducationModel.getCityId()));
        institutionEducation.setAddress(institutionEducationModel.getAddress());
        institutionEducation.setPostalCode(institutionEducationModel.getPostalCode());
        return institutionEducation;
    }
}
